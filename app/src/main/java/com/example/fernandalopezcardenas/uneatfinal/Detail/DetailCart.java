package com.example.fernandalopezcardenas.uneatfinal.Detail;

import java.io.Serializable;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailCart implements Serializable {
    String uidclient;
    String uidrestaurant;
    String uidrequest;
    String message;
    DetailPlate cart;

    public DetailCart(String uidclient, String uidrestaurant, String uidrequest, String message, DetailPlate cart) {
        this.uidclient = uidclient;
        this.uidrestaurant = uidrestaurant;
        this.uidrequest = uidrequest;
        this.message = message;
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
    public String getUidrequest() {
        return uidrequest;
    }
    public void setUidrequest(String uidrequest) {
        this.uidrequest = uidrequest;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public DetailPlate getCart() {
        return cart;
    }
    public void setCart(DetailPlate cart) {
        this.cart = cart;
    }
}
