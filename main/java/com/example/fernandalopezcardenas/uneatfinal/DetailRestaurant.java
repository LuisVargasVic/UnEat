package com.example.fernandalopezcardenas.uneatfinal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailRestaurant implements Serializable{
    String Name;
    ArrayList<DetailPlate> Plate;

    public DetailRestaurant(){

    }

    public DetailRestaurant(String Name, ArrayList<DetailPlate> Plate ) {
        this.Name = Name;
        this.Plate = Plate;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<DetailPlate> getPlate() {
        return Plate;
    }

    public void setPlate(ArrayList<DetailPlate> Plate ) {
        this.Plate = Plate;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
