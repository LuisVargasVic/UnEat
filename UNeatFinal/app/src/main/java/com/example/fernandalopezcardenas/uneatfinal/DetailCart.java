package com.example.fernandalopezcardenas.uneatfinal;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailCart {
    String name;
    String price;

    public DetailCart(){

    }
    public DetailCart(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
