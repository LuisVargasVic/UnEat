package com.example.fernandalopezcardenas.uneatfinal.Detail;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailPlate implements Serializable{

    private String Name;
    private int Price;
    private String uidrequest;
    private String uidrestaurant;
    private ArrayList<String> Ingredients;
    private String ImagePlate;

    public DetailPlate(){

    }
    public DetailPlate(String name, int price, String uidrequest, String uidrestaurant, ArrayList<String> ingredients) {
        Name = name;
        Price = price;
        this.uidrequest = uidrequest;
        this.uidrestaurant = uidrestaurant;
        Ingredients = ingredients;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getUidrequest() {
        return uidrequest;
    }

    public void setUidrequest(String uidrequest) {
        this.uidrequest = uidrequest;
    }

    public String getUidrestaurant() {
        return uidrestaurant;
    }

    public void setUidrestaurant(String uidrestaurant) {
        this.uidrestaurant = uidrestaurant;
    }

    public ArrayList<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        Ingredients = ingredients;
    }

    public String getImagePlate() {
        return ImagePlate;
    }

    public void setImagePlate(String imagePlate) {
        ImagePlate = imagePlate;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
