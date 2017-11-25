package com.example.fernandalopezcardenas.uneatfinal.Detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailRestaurant implements Serializable {
    private String name;
    private String uid;
    private String ImageRestaurant;
    private HashMap<String, DetailPlate> plates;

    public DetailRestaurant(){
        this.plates = new HashMap<String, DetailPlate>();
    }

    public DetailRestaurant(String ImageRestaurant){
        this();
        this.ImageRestaurant = ImageRestaurant;
    }

    public DetailRestaurant(String name, String uid, String ImageRestaurant) {
        this();
        this.name = name;
        this.uid = uid;
        this.ImageRestaurant = ImageRestaurant;
    }

    public DetailRestaurant(String name, String uid, String ImageRestaurant, HashMap<String, DetailPlate> plates) {
        this.name = name;
        this.uid=uid;
        this.ImageRestaurant = ImageRestaurant;
        this.plates = plates;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getImageRestaurant() {
        return ImageRestaurant;
    }
    public void setImageRestaurant(String imageRestaurant) {
        ImageRestaurant = imageRestaurant;
    }
    public void setPlates(HashMap<String, DetailPlate> plates) {
        this.plates = plates;
    }
    public HashMap<String, DetailPlate> getPlates(){ return plates; }
    public ArrayList<DetailPlate> getPlatesList() {
        ArrayList<DetailPlate> plates = new ArrayList<DetailPlate>();
        for (DetailPlate p : this.plates.values()){
            plates.add(p);
        }
        return plates;

    }
    @Override
    public String toString() {
        return this.getName();
    }

}
