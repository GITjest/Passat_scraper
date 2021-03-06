package com.example.webscraper.controller;

import com.example.webscraper.model.PassatEntity;
import com.example.webscraper.request.SearchBaseConfig;
import com.example.webscraper.request.SearchConfig;
import com.example.webscraper.response.PassatResponse;
import com.example.webscraper.scraper.OLX;
import com.example.webscraper.scraper.Otomoto;
import com.example.webscraper.scraper.PassatScraper;
import com.example.webscraper.service.PassatService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final String START_URL = "https://www.olx.pl/motoryzacja/samochody/q-passat/";

    private final PassatService passatService;

    public MainController(PassatService passatService) {
        this.passatService = passatService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("searchConfig", new SearchConfig(1, 15, new Date()));
        return "index";
    }

    @PostMapping(value = "/search", consumes = "application/json", produces = "application/json")
    public @ResponseBody PassatResponse searchPassats(@Valid @RequestBody SearchConfig searchConfig, BindingResult bindingResult) {
        PassatResponse passatResponse = new PassatResponse();
        passatResponse.setValidated(false);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            passatResponse.setErrorMessages(errors);
        } else {
            List<PassatEntity> passats = getPassats(searchConfig);
            if(passats.size() > 0) {
                passats.sort(Comparator.comparing(PassatEntity::getMileage));
                passatResponse.setPassats(passats);
                passatResponse.setValidated(true);
            } else {
                passatResponse.setErrorMessages(Collections.singletonMap("nothingFound", "Nothing found!"));
            }
        }
        return passatResponse;
    }

    private List<PassatEntity> getPassats(SearchConfig searchConfig) {
        Set<String> visited = new HashSet<>();
        List<PassatEntity> passats = new ArrayList<>();
        String url = START_URL;
        try {
            for (int i = 0; i < searchConfig.getPageLimit(); i++) {
                Document doc = Jsoup.connect(url).get();
                if (!visited.contains(url)) {
                    Elements links = doc.getElementsByClass("title-cell ").select("a");
                    for (Element element : links) {
                        String offerLink = element.attr("href");
                        if (!visited.contains(offerLink)) {
                            Document offerPage = Jsoup.connect(offerLink).get();
                            PassatScraper passatScraper = setPassatScraper(offerPage);
                            if (passatScraper != null) {
                                if (passatScraper.getPassat().getPublishDate().after(searchConfig.getDateLimit())) {
                                    passats.add(passatScraper.getPassat());
                                    if (passats.size() >= searchConfig.getPassatLimit()) return passats;
                                }
                            }
                        }
                        visited.add(offerLink);
                    }
                    visited.add(url);
                }
                url = doc.getElementsByClass("fbold next abs large").select("a").attr("href");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return passats;
        }
        return passats;
    }

    private PassatScraper setPassatScraper(Document document) {
        String uri = document.baseUri();
        if (uri.contains("olx.pl/oferta/")) {
            return new OLX(document);
        } else if (uri.contains("otomoto.pl/oferta/")) {
            return new Otomoto(document);
        }
        return null;
    }

    @PostMapping(value = "/getPassatsFromBase", consumes = "application/json", produces = "application/json")
    public @ResponseBody List<PassatEntity> getPassatsFromBase(@RequestBody SearchBaseConfig searchBaseConfig) {
        List<PassatEntity> passats;
        if(searchBaseConfig.getStartDate() == null || searchBaseConfig.getEndDate() == null) {
            passats = passatService.find();
        } else {
            passats = passatService.find(searchBaseConfig.getStartDate(), searchBaseConfig.getEndDate());
        }
        passats.sort(Comparator.comparing(PassatEntity::getMileage));
        return passats;
    }

    @PostMapping("/saveSearchResult")
    public @ResponseBody int saveSearchResult(@RequestBody List<PassatEntity> passats) {
        passatService.save(passats);
        return passats.size();
    }


}
