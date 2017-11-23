package com.example.fernandalopezcardenas.uneatfinal.Detail;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailPlate implements Serializable{
    String Name;
    int Price;
    String uidrequest;
    String uidrestaurant;
    ArrayList<String> Ingredients;

    public DetailPlate(){

    }

    public DetailPlate(String Name, int Price, String uidrequest, String uidrestaurant, ArrayList<String> Ingredients) {
        this.Name = Name;
        this.Price = Price;
        this.uidrequest = uidrequest;
        this.uidrestaurant = uidrestaurant;
        this.Ingredients = Ingredients;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public int getPrice() {
        return Price;
    }
    public void setPrice(int Price) {
        this.Price = Price;
    }
    public ArrayList<String> getIngredients() {return Ingredients;}
    public void setIngredients(ArrayList<String> Ingredients) {
        this.Ingredients = Ingredients;
    }
    public String getUidrequest() {
        return uidrequest;
    }
    public void setUidrequest(String uidrequest) {
        this.uidrequest = uidrequest;
    }
    public String getUidrestaurant(){
        return uidrestaurant;
    }
    public void setUidrestaurant(String uidrestaurant){this.uidrestaurant = uidrestaurant; }

    @Override
    public String toString() {
        return this.getName();
    }
}
