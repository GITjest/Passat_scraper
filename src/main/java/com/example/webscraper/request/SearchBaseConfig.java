package com.example.webscraper.request;

import java.util.Date;

public class SearchBaseConfig {
    private Date startDate;
    private Date endDate;

    public SearchBaseConfig() {
    }

    public SearchBaseConfig(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchBaseConfig{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
