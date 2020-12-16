package com.netcracker.model;

public class Order {
    private int orderId;
    private String name;
    private String status;
    private int destinationID;
    private int driverId;
    private int boxId;
    private int price;
    private int receiverId;

    public Order(int orderId, String name, String status, int destinationID,
                 int driverId, int boxId, int price, int receiverId) {
        this.orderId = orderId;
        this.name = name;
        this.status = status;
        this.destinationID = destinationID;
        this.driverId = driverId;
        this.boxId = boxId;
        this.price = price;
        this.receiverId = receiverId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
