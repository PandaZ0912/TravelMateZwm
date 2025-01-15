package com.example.travelmate.models;

public class TravelLog {
    private int id;
    private String title;
    private String content;
    private String location;
    private String date;
    private String imagePath;

    public TravelLog(int id, String title, String content, String location, String date, String imagePath) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.location = location;
        this.date = date;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getImagePath() {
        return imagePath;
    }
}
