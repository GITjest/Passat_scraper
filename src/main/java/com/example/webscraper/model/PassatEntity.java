package com.example.webscraper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passat", schema = "passat_scraper")
public class PassatEntity {
    private int passatId;
    private String name;
    private int price;
    private int mileage;
    private Date publishDate;

    public PassatEntity() {
    }

    public PassatEntity(String name, int price, int mileage, Date publishDate) {
        this.name = name;
        this.price = price;
        this.mileage = mileage;
        this.publishDate = publishDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passat_id")
    public int getPassatId() {
        return passatId;
    }

    public void setPassatId(int passatId) {
        this.passatId = passatId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "mileage")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Basic
    @Column(name = "publish_date")
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "PassatEntity{" +
                "id=" + passatId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", publishDate=" + publishDate +
                '}';
    }
}
