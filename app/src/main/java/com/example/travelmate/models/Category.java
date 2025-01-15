package com.example.travelmate.models;

import java.util.List;

public class Category {
    private String provinceName;
    private List<String> cities;

    public Category(String provinceName, List<String> cities) {
        this.provinceName = provinceName;
        this.cities = cities;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
