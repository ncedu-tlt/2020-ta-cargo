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

    @OneToOne
    @JoinColumn(name = "destinationId")
    private Address destinationId;

    @OneToOne
    @JoinColumn(name = "locationId")
    private Address locationId;

    @OneToOne
    @JoinColumn(name = "driverId")
    private Client driverId;

    @OneToOne
    @JoinColumn(name = "boxId")
    private Box boxId;

    @Column (name = "price")
    private int price;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private Client receiverId;

    @OneToOne
    @JoinColumn( name = "status_id")
    private Status status;

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

    public Address getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Address destinationId) {
        this.destinationId = destinationId;
    }

    public Address getLocationId() {
        return locationId;
    }

    public void setLocationId(Address locationId) {
        this.locationId = locationId;
    }

    public Client getDriverId() {
        return driverId;
    }

    public void setDriverId(Client driverId) {
        this.driverId = driverId;
    }

    public Box getBoxId() {
        return boxId;
    }

    public void setBoxId(Box boxId) {
        this.boxId = boxId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Client getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Client receiverId) {
        this.receiverId = receiverId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
