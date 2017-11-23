package com.example.fernandalopezcardenas.uneatfinal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailCart implements Serializable {
    String id;
    Date order;
    DetailRestaurant cart;

    public Date getOrder() {
        return order;
    }

    public void setOrder(Date order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DetailRestaurant getCart() {
        return cart;
    }

    public void setCart(DetailRestaurant cart) {
        this.cart = cart;
    }

    public DetailCart() {

    }


}
