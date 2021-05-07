package com.example.miniproject;

import android.graphics.Bitmap;

public class Model {

    Bitmap imageBitmap;

    String name;
    String price;
    String location;
    String hectare;
    String farmerid;
    String yeild;
    String cid;


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHectare() {
        return hectare;
    }

    public void setHectare(String hectare) {
        this.hectare = hectare;
    }

    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Model(String name, String unit, Bitmap imageBitmap, String cid, String hectare, String price, String location, String farmerid ) {
        this.imageBitmap = imageBitmap;
        this.name=name;
        this.yeild=unit;
        this.cid = cid;
        this.price = price;
        this.location = location;
        this.hectare = hectare;
        this.farmerid = farmerid;
    }
    public String getCid() {
        return cid;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }



    public String getYeild() {
        return yeild;
    }

    public void setYeild(String yeild) {
        this.yeild = yeild;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
