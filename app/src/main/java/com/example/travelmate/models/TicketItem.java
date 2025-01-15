package com.example.travelmate.models;

public class TicketItem {

    private String name;  // 景区名称
    private String description;  // 景区描述
    private int imageResId;  // 图片资源ID
    private double price;  // 票价
    private float rating;  // 评分

    public TicketItem(String name, String description, int imageResId, double price, float rating) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.price = price;
        this.rating = rating;
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

    public double getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }
}


