package com.netcracker.model;

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

    @Column(name = "liftingCapacity")
    private Integer liftingCapacity;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",  referencedColumnName = "userId")
    private Client user;

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

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        if(user != null) {
            this.user = user;
        }
    }
}
