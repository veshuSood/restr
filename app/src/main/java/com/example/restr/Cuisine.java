package com.example.restr;

public class Cuisine {
    String name;
    int imageResId;

    public Cuisine(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;}
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
