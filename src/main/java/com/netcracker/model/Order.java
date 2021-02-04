package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(name = "cg_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "status")
    private  String status;

    @Column (name = "destination_id")
    private int destinationId;

    @Column (name = "driver_id")
    private int driverId;

    @Column (name = "box_id")
    private int boxId;

    @Column (name = "price")
    private int price;

    @Column (name = "receiver_id")
    private int receiverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return destinationId;
    }

    public void setDestinationID(int destinationID) {
        this.destinationId = destinationId;
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
