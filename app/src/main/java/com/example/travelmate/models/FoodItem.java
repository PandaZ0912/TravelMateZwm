package com.example.travelmate.models;

public class FoodItem {

    private String name;
    private String description;
    private int imageResId;
    private double rating;
    private int price;

    public FoodItem(String name, String description, int imageResId, double rating, int price) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }
}

