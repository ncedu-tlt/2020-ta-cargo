package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.model.Address;
import com.netcracker.service.AddressService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public Address create(@RequestBody Address address){
        addressService.create(address);
        return address;
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> displayAll(){
        final List<Address> addressList = addressService.displayAll();
        return addressList != null && !addressList.isEmpty()
                ? new ResponseEntity<>(addressList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = addressService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
