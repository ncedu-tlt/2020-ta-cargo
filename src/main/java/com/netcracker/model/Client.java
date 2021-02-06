package com.netcracker.model;

import javax.persistence.*;

@Entity
@Table(name = "cg_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "middlename")
    private String middleName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "drive_category")
    private String driveCategory;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        if (userId != null) {
            this.userId = userId;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if ((lastName != null) && (!lastName.isEmpty())) {
            this.lastName = lastName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if ((firstName != null) && (!firstName.isEmpty())) {
            this.firstName = firstName;
        }
    }
}