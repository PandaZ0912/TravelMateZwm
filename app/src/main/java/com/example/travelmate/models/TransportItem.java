package com.example.travelmate.models;

public class TransportItem {

    private String trainNo;         // 列车号
    private String departureTime;   // 出发时间
    private String arrivalTime;     // 到达时间
    private String departure;       // 出发地点
    private String arrival;         // 到达地点
    private double price;           // 票价，建议使用 double 类型，以支持小数
    private String seatType;        // 座位类型

    // 构造方法
    public TransportItem(String trainNo, String departureTime, String arrivalTime,
                         String departure, String arrival, double price, String seatType) {
        this.trainNo = trainNo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.seatType = seatType;
    }

    // Getter 方法
    public String getTrainNo() {
        return trainNo;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public double getPrice() {
        return price;
    }

    public String getSeatType() {
        return seatType;
    }

    // 设置价格的 Getter 和 Setter（如果需要）
    public void setPrice(double price) {
        this.price = price;
    }
}
