package com.example.travelmate.models;

public class FoodItem {
    private String name;
    private String description;
    private float rating;
    private double price;
    private int imageResId;

    public FoodItem(String name, String description, float rating, double price, int imageResId) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
