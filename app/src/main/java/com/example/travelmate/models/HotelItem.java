package com.example.travelmate.models;

public class HotelItem {

    private String name;           // 酒店名称
    private String description;    // 酒店描述
    private int imageResId;        // 酒店图片资源ID
    private double rating;         // 酒店评分
    private int price;             // 酒店价格
    private String address;        // 酒店地址
    private String hotelType;      // 酒店类型（例如：五星级、四星级等）

    // 包含所有详细信息的构造函数
    public HotelItem(String name, String description, int imageResId, double rating, int price, String address, String hotelType) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
        this.address = address;
        this.hotelType = hotelType;
    }

    // 基本构造函数（不包含酒店地址和类型）
    public HotelItem(String name, String description, int imageResId, double rating, int price) {
        this(name, description, imageResId, rating, price, "", "");
    }

    // Getter and Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    // toString 方法，方便调试输出
    @Override
    public String toString() {
        return "HotelItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", hotelType='" + hotelType + '\'' +
                '}';
    }
}
