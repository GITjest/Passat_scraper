package com.example.webscraper.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OLX extends PassatScraper {

    private final Element offerDescription;

    public OLX(Document doc) {
        super(doc);
        if(!doc.baseUri().contains("olx.pl/oferta/")) {
            throw new IllegalArgumentException("Invalid address");
        }
        offerDescription = doc.getElementById("offerdescription");
        passat.setName(extractName());
        passat.setPrice(extractPrice());
        passat.setMileage(extractMileage());
        passat.setPublishDate(extractPublishDate());
    }

    private String extractName() {
        return offerDescription.selectFirst("h1").text();
    }

    private int extractPrice() {
        String price = offerDescription.getElementsByClass("pricelabel").first().text();
        price = price.replaceAll("\\D+", "");
        return Integer.parseInt(price);
    }

    private int extractMileage() {
        String mileage = "0";
        for (Element e : offerDescription.getElementsByClass("offer-details__item")) {
            if(e.text().contains("Przebieg")) {
                mileage = e.text().replaceAll("\\D+", "");
            }
        }
        return Integer.parseInt(mileage);
    }

    private Date extractPublishDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm,dd MMMM yyyy", new Locale("pl"));
            String date = offerDescription.select("em").text();
            date = date.substring(indexOfFirstDigit(date));
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    private int indexOfFirstDigit(String str) {
        String[] n = str.split("");
        for (int i = 0; i < n.length; i++) {
            if(n[i].matches("[0-9]+")) {
                return i;
            }
        }
        return 0;
    }
}
