package com.example.croppriceapp;

public class auctioFetchModel {
    String id;
    String auction;
    String auc_by;
    String product;
    String description;
    String price;
    String start_date;
    String end_date;

    public auctioFetchModel(String id, String auction, String auc_by, String product, String description, String price, String start_date, String end_date) {
        this.id = id;
        this.auction = auction;
        this.auc_by = auc_by;
        this.product = product;
        this.description = description;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getAuc_by() {
        return auc_by;
    }

    public String getId() {
        return id;
    }

    public String getAuction() {
        return auction;
    }

    public String getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }
}
