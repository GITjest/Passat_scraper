package com.example.webscraper.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Otomoto extends PassatScraper {

    public Otomoto(Document doc) {
        super(doc);
        if(!doc.baseUri().contains("otomoto.pl/oferta/")) {
            throw new IllegalArgumentException("Invalid address");
        }
        passat.setName(extractName());
        passat.setPrice(extractPrice());
        passat.setMileage(extractMileage());
        passat.setPublishDate(extractPublishDate());
    }

    private String extractName() {
        return doc.getElementsByClass("offer-title big-text fake-title").first().text();
    }

    private int extractPrice() {
        String price = doc.getElementsByClass("offer-price__number").first().text();
        price = price.replaceAll("\\D+", "");
        return Integer.parseInt(price);
    }

    private int extractMileage() {
        String mileage = "0";
        for (Element e : doc.getElementsByClass("offer-params__item")) {
            if(e.text().contains("Przebieg")) {
                mileage = e.text().replaceAll("\\D+", "");
            }
        }
        return Integer.parseInt(mileage);
    }

    private Date extractPublishDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm,dd MMMM yyyy", new Locale("pl"));
            String date = doc.getElementsByClass("offer-meta__value").first().text();
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
