package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.model.Client;
import com.netcracker.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PatchMapping("/client")
    public ResponseEntity<?> modify(@RequestBody Client client ){
        final boolean updateField = clientService.modify(client);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}