package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(name = "cg_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer id;

    @Column (name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "destinationId")
    private Address destination;

    @OneToOne
    @JoinColumn(name = "locationId")
    private Address location;

    @OneToOne
    @JoinColumn(name = "driverId")
    private Client driver;

    @OneToOne
    @JoinColumn(name = "boxId")
    private Box box;

    @Column (name = "price")
    private int price;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private Client receiver;

    @OneToOne
    @JoinColumn( name = "status_id")
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getDestination() {
        return destination;
    }

    public void setDestination(Address destinationId) {
        this.destination = destinationId;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address locationId) {
        this.location = locationId;
    }

    public Client getDriver() {
        return driver;
    }

    public void setDriver(Client driverId) {
        this.driver = driverId;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box boxId) {
        this.box = boxId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiverId) {
        this.receiver = receiverId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
