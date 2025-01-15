package com.example.travelmate.models;

public class RankItem {

    private int id;// 项目的ID
    private String title; // 项目的标题
    private String description; // 项目的描述
    private double rating; // 项目的评分
    private Integer imageResId; // 图片资源ID（如果是内置资源）
    private String imagePath; // 图片的存储路径（如果是外部存储）

    public RankItem(int id, String title, String description, double rating, Integer imageResId, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.imageResId = imageResId;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Integer getImageResId() {
        return imageResId;
    }

    public void setImageResId(Integer imageResId) {
        this.imageResId = imageResId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
