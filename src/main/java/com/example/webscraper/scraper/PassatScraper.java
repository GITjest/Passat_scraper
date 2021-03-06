package com.example.webscraper.scraper;

import com.example.webscraper.model.PassatEntity;
import org.jsoup.nodes.Document;

public class PassatScraper {
    protected PassatEntity passat;
    protected Document doc;

    public PassatScraper(Document doc) {
        if(doc == null) {
            throw new IllegalArgumentException("Document can not be null");
        }
        this.doc = doc;
        passat = new PassatEntity();
    }

    public PassatEntity getPassat() {
        return passat;
    }
}
