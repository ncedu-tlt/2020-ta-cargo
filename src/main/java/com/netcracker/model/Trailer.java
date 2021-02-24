package com.netcracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "cg_trailer")
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "lifting_capacity")
    private Integer liftingCapacity;

    @JsonBackReference
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if(id != null) {
            this.id = id;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if((number != null) && (!number.isEmpty())) {
            this.number = number;
        }
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        if(volume != null) {
            this.volume = volume;
        }
    }

    public Integer getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(Integer liftingCapacity) {
        if(liftingCapacity != null) {
            this.liftingCapacity = liftingCapacity;
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if(car != null) {
            this.car = car;
        }
    }
}
