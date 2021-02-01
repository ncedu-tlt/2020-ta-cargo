package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.model.Address;
import com.netcracker.service.AddressService;

import java.util.List;

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

    @GetMapping("/address")
    public ResponseEntity<List<Address>>readeAll(){
        final List<Address> addressList = addressService.readAll();
        return addressList != null && !addressList.isEmpty()
                ? new ResponseEntity<>(addressList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> readById(@PathVariable(name = "id") int id){
        final Address address = addressService.readById(id);
        return address != null
                ? new ResponseEntity<>(address, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/address")
    public ResponseEntity<?> update(@RequestBody Address address){
        final  boolean update = addressService.update(address);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = addressService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PatchMapping("/address")
    public ResponseEntity<?> updatePartial(@RequestBody Address address ){
        final boolean updateField = addressService.updatePartial(address);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
