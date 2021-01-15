package com.netcracker.model;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "drivecategory")
    private String driveCategory;

    @OneToMany(mappedBy = "cg_client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;



    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        if((cars != null) && (!cars.isEmpty())) {
            this.cars = cars;
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        if (userId != null)  {
            this.userId = userId;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if ((lastName != null)&&(!lastName.isEmpty())) {
            this.lastName = lastName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if ((firstName != null)&&(!firstName.isEmpty())) {
            this.firstName = firstName;
        }
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        if ((middleName != null)&&(!middleName.isEmpty())) {
            this.middleName = middleName;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if ((phone != null)&&(!phone.isEmpty())) {
            this.phone = phone;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if ((email != null)&&(!email.isEmpty())) {
            this.email = email;
        }
    }

    public String getDriveCategory() {
        return driveCategory;
    }

    public void setDriveCategory(String driveCategory) {
        if ((driveCategory != null)&&(!driveCategory.isEmpty())) {
            this.driveCategory = driveCategory;
        }
    }
}
