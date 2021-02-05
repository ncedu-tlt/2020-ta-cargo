package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.model.Client;
import com.netcracker.service.ClientService;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/client")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}