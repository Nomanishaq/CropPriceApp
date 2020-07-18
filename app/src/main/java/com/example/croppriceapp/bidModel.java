package com.example.croppriceapp;

public class bidModel {

    String auction_name;
    String product_name;
    String product_auction;
    String product_price;

    public bidModel( String auction_name, String product_name, String product_auction, String product_price) {
        this.auction_name = auction_name;
        this.product_name = product_name;
        this.product_auction = product_auction;
        this.product_price = product_price;
    }


    public String getAuction_name() {
        return auction_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_auction() {
        return product_auction;
    }

    public String getProduct_price() {
        return product_price;
    }
}
