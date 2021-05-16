package com.netcracker.model;

import javax.persistence.*;


@Entity
@Table(name = "cg_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer addressId;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private String home;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "lng")
    private Number lng;

    @Column(name = "lat")
    private Number lat;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        if(addressId != null) {
            this.addressId = addressId;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if((country != null)&&(!country.isEmpty())) {
            this.country = country;
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if((city != null)&&(!city.isEmpty())) {
            this.city = city;
        }
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if((street != null)&&(!street.isEmpty())) {
            this.street = street;
        }
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        if((home != null)&&(!home.isEmpty())) {
            this.home = home;
        }
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        if((apartment != null)&&(!apartment.isEmpty())) {
            this.apartment = apartment;
        }
    }

    public Number getLng() {
        return lng;
    }

    public void setLng(Number lng) {
        if((apartment != null)&&(!apartment.isEmpty())) {
            this.apartment = apartment;
        }
    }

    public Number getLat() {
        return lat;
    }

    public void setLat(Number lat) {
        if((apartment != null)&&(!apartment.isEmpty())) {
            this.apartment = apartment;
        }
    }
}
