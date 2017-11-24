package com.example.fernandalopezcardenas.uneatfinal.Detail;

/**
 * Created by fernandalopezcardenas on 20/09/17.
 */

public class DetailUser {
    String name;
    String rol;

    public DetailUser(){

    }
    public DetailUser(String name, String rol) {
        this.name = name;
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }



}
