package com.example.fernandalopezcardenas.uneatfinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailCart implements Serializable {
    String uidclient;
    String uidrestaurant;
    DetailPlate cart;

    public DetailCart(String uidclient, String uidrestaurant, DetailPlate cart) {
        this.uidclient = uidclient;
        this.uidrestaurant = uidrestaurant;
        this.cart = cart;
    }

    public DetailCart() {

    }

    public String getUidclient() {
        return uidclient;
    }

    public void setUidclient(String uidclient) {
        this.uidclient = uidclient;
    }

    public String getUidrestaurant() {
        return uidrestaurant;
    }

    public void setUidrestaurant(String uidrestaurant) {
        this.uidrestaurant = uidrestaurant;
    }

    public DetailPlate getCart() {
        return cart;
    }

    public void setCart(DetailPlate cart) {
        this.cart = cart;
    }
}
