package com.netcracker.controller;

import com.netcracker.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.service.AddressService;

@RestController
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<?> create(@RequestBody Address address){
        addressService.create(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = addressService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
