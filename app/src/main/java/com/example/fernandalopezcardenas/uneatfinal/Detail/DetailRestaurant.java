package com.example.fernandalopezcardenas.uneatfinal.Detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailRestaurant implements Serializable{
    String Name;
    String UID;
    //String Image;
    HashMap<String,DetailPlate> Plates;

    public DetailRestaurant( ){

    }

    public DetailRestaurant(String Name, String UID){
        this.Name = Name;
        this.UID=UID;
    }

    public DetailRestaurant(String Name, String UID, /*String Image,*/ HashMap<String, DetailPlate> Plates) {
        this.Name = Name;
        this.UID=UID;
        //this.Image = Image;
        this.Plates = Plates;
    }

    public String getName() {
        return Name;
    }
    //public String getImage() {return Image;}
    public void setName(String Name) {
        this.Name = Name;
    }
    //public void setImage(String Image){this.Image = Image;}
    public HashMap<String, DetailPlate> getPlates() {
        return Plates;
    }
    public ArrayList<DetailPlate> getPlatesList() {
        return new ArrayList<>(this.Plates.values());
    }
    public void setPlates(HashMap<String, DetailPlate> Plates ) {
        this.Plates = Plates;
    }
    public String getUID() {
        return UID;
    }
    public void setUID(String UID) {
        this.UID = UID;
    }
}
