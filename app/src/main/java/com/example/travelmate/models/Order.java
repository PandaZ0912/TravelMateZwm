package com.example.travelmate.models;

import java.io.Serializable;

public class Order implements Serializable {
    private String title;
    private String date;
    private String price;
    private OrderType orderType;  // 新增字段，用于区分订单类型

    // 构造函数，接受订单类型
    public Order(String title, String date, String price, OrderType orderType) {
        this.title = title;
        this.date = date;
        this.price = price;
        this.orderType = orderType;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public enum OrderType {
        TRANSPORT, HOTEL, TICKET
    }
}
