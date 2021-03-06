package com.example.webscraper.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class SearchConfig {
    private int pageLimit;
    private int passatLimit;
    private Date dateLimit;

    public SearchConfig() {
    }

    public SearchConfig(int pageLimit, int passatLimit, Date dateLimit) {
        this.pageLimit = pageLimit;
        this.passatLimit = passatLimit;
        this.dateLimit = dateLimit;
    }

    @NotNull(message = "Please enter page limit")
    @Min(value = 1, message = "Page limit can not be less then 1")
    @Max(value = 5, message = "Page limit can not be greater then 5")
    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    @NotNull(message = "Please enter passat limit")
    @Min(value = 1, message = "Passat limit can not be less then 1")
    @Max(value = 60, message = "Passat limit can not be greater then 60")
    public int getPassatLimit() {
        return passatLimit;
    }

    public void setPassatLimit(int passatLimit) {
        this.passatLimit = passatLimit;
    }

    @NotNull(message = "Please enter date limit")
    @Past(message = "Please enter a date in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Date dateLimit) {
        this.dateLimit = dateLimit;
    }

    @Override
    public String toString() {
        return "SearchConfig{" +
                "pageLimit=" + pageLimit +
                ", passatLimit=" + passatLimit +
                ", dateLimit=" + dateLimit +
                '}';
    }
}
