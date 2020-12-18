package com.netcracker.model;

public class Box {
    private String name;
    private int boxId;
    private int height;
    private int width;
    private int weight;
    private int volume;
    private int clientId;
    private int typeId;

    public Box(String name, int boxId, int height, int width, int weight, int volume, int clientId, int typeId) {
        this.name = name;
        this.boxId = boxId;
        this.height = height;
        this.width = width;
        this.weight = weight;
        this.volume = volume;
        this.clientId = clientId;
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
