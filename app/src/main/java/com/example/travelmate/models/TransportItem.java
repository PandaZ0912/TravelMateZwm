package com.example.travelmate.models;

public class TransportItem {

    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private String fromStation;
    private String toStation;
    private int price;
    private String seatType;

    public TransportItem(String trainName, String departureTime, String arrivalTime, String fromStation, String toStation, int price, String seatType) {
        this.trainName = trainName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.price = price;
        this.seatType = seatType;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public int getPrice() {
        return price;
    }

    public String getSeatType() {
        return seatType;
    }
}
