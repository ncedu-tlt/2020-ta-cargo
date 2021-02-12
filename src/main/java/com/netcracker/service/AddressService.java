package com.netcracker.service;

import com.netcracker.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.model.Address;


import java.util.List;

@Service
public class AddressService implements Serviceable<Address> {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void create(Address address){
        addressRepository.saveAndFlush(address);
    }

    @Override
    public List<Address> displayAll(){
        return addressRepository.findAll();
    }

    public Address displayById(Integer id){
        return addressRepository.findById(id).get();
    }

    @Override
    public boolean delete(Integer id){
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }else return false;
    }

    public boolean modify(Address address){
        if (addressRepository.existsById(address.getAddressId())) {
            Address addressForModify = displayById(address.getAddressId());
            addressForModify.setAddressId(address.getAddressId());
            addressForModify.setCountry(address.getCountry());
            addressForModify.setCity(address.getCity());
            addressForModify.setStreet(address.getStreet());
            addressForModify.setHome(address.getHome());
            addressForModify.setApartment(address.getApartment());
            addressRepository.saveAndFlush(addressForModify);
            return true;
        }else  return false;
    }
}
