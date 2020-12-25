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
        if (orderId != 0) {
            this.orderId = getOrderId();
        }
        return orderId;
    }

    public void setOrderId(int orderId) {

        this.orderId = orderId;
    }

    public String getName() {
        if (name != null) {
            this.name = getName();
        }
        return getName();
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        if (status != null) {
            this.status = getStatus();
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDestinationID() {
        if (destinationID != 0) {
            this.destinationID = getDestinationID();
        }
        return destinationID;
    }

    public void setDestinationID(int destinationID) {
        this.destinationID = destinationID;
    }

    public int getDriverId() {
        if (driverId != 0) {
            this.driverId = getDriverId();
        }
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getBoxId() {
        if (boxId != 0) {
            this.boxId = getBoxId();
        }
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public int getPrice() {
        if (price != 0) {
            this.price = getPrice();
        }
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReceiverId() {
        if (receiverId != 0) {
            this.receiverId = getReceiverId();
        }
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }
}
