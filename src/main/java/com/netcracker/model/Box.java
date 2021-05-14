package com.netcracker.model;


import javax.persistence.*;
import java.util.Objects;

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
    private Double volume;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne
    @JoinColumn(name = "type_id")
    private TypeCargo typeCargo;

    @Column(name = "weight")
    private Double weight;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client != null) {
            this.client = client;
        }
    }

    public TypeCargo getTypeCargo() {
        return typeCargo;
    }

    public void setTypeCargo(TypeCargo typeCargo) {
        if (typeCargo != null) {
            this.typeCargo = typeCargo;
        }
    }

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        if ((boxId != null)) {
            this.boxId = boxId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ((name != null) && (!name.isEmpty())) {
            this.name = name;
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        if ((height != null)) {
            this.height = height;
        }
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        if ((width != null)) {
            this.width = width;
        }
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        if ((volume != null)) {
            this.volume = volume;
        }
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if ((weight != null)) {
            this.weight = weight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return Objects.equals(boxId, box.boxId) && Objects.equals(name, box.name) && Objects.equals(height, box.height) && Objects.equals(width, box.width) && Objects.equals(volume, box.volume) && Objects.equals(client, box.client) && Objects.equals(typeCargo, box.typeCargo) && Objects.equals(weight, box.weight);
    }
}
