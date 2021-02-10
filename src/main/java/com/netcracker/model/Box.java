package com.netcracker.model;


import javax.persistence.*;

@Entity
@Table(name = "cg_box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "box_id")
    private Integer boxId;

    @Column(name = "name")
    private String name;

    @Column(name = "height")
    private Integer height;

    @Column(name = "widht")
    private Integer widht;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "current_location")
    private Integer currentLocation;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "weight")
    private Integer weight;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        if((boxId != null)) {
            this.boxId = boxId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if((name != null) && (!name.isEmpty())) {
            this.name = name;
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        if((height != null) ) {
            this.height = height;
        }
    }

    public Integer getWidht() {
        return widht;
    }

    public void setWidht(Integer widht) {
        if((widht != null)) {
            this.widht = widht;
        }
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        if((volume != null)) {
            this.volume = volume;
        }
    }

    public Integer getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Integer currentLocation) {
        if((currentLocation != null)) {
            this.currentLocation = currentLocation;
        }
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        if((clientId != null)) {
            this.clientId = clientId;
        }
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        if((typeId != null)) {
            this.typeId = typeId;
        }
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        if((weight != null) ) {
            this.weight = weight;
        }
    }
}