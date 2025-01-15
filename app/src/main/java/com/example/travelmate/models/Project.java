package com.example.travelmate.models;

public class Project {
    private String projectName;   // 项目名称
    private String description;   // 项目简介
    private String price;         // 项目价格
    private int imageResId;       // 项目图片资源 ID

    public Project(String projectName, String description, String price, int imageResId) {
        this.projectName = projectName;
        this.description = description;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
