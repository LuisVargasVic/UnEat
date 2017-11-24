package com.example.fernandalopezcardenas.uneatfinal.Detail;

/**
 * Created by gabotrugomez on 11/13/17.
 */

public class ListModelRestaurant {
    private String RestaurantName="";
    private String ImageRestaurant = "";
    private String RestaurantURL=  "";


    public void setRestaurantName(String RestaurantName)
    {
        this.RestaurantName = RestaurantName;
    }

    public void setImageRestaurant(String Image)
    {
        this.ImageRestaurant = ImageRestaurant;
    }

    public void setRestaurantURL(String Url)
    {
        this.RestaurantURL = RestaurantURL;
    }



    /*********** Get Methods ****************/

    public String getRestaurantName()
    {
        return this.RestaurantName;
    }

    public String getImageRestaurant()
    {
        return this.ImageRestaurant;
    }

    public String getRestaurantURL()
    {
        return this.RestaurantURL;
    }




}
