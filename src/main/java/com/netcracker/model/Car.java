package com.netcracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "cg_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "lifting_capacity")
    private Integer liftingCapacity;

    @JsonBackReference
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @OneToOne
    @JoinColumn(name = "user_id")
    private Client client;


    @OneToOne(mappedBy = "car", orphanRemoval = true)
    private Trailer trailer;

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        if(trailer != null) {
            this.trailer = trailer;
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        if(volume != null) {
            this.volume = volume;
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if(client != null) {
            this.client = client;
        }
    }
}
