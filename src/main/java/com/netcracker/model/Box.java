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

    @Column(name = "width")
    private Integer width;

    @Column(name = "volume")
    private Integer volume;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn (name="type_id")
    private TypeCargo typeCargo;

    @Column(name = "weight")
    private Integer weight;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if(client != null) {
            this.client = client;
        }
    }

    public TypeCargo getTypeCargo() {
        return typeCargo;
    }

    public void setTypeCargo(TypeCargo typeCargo) {
        if(typeCargo != null) {
            this.typeCargo = typeCargo;
        }
    }

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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        if((width != null)) {
            this.width = width;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        if((weight != null) ) {
            this.weight = weight;
        }
    }
}
