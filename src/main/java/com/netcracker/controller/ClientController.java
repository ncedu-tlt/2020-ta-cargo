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

    @PostMapping("/client")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = clientService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> displayAll(){
        final List<Client> clientList = clientService.displayAll();
        return clientList != null && !clientList.isEmpty()
                ? new ResponseEntity<>(clientList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/client/email/{email}")
    public ResponseEntity<Client> displayByEmail(@PathVariable(name = "email") String email){
        final Client client = clientService.displayByEmail(email);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> displayById(@PathVariable(name = "id") int id){
        final Client client = clientService.displayById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/client/phone/{phone}")
    public ResponseEntity<Client> displayByPhone(@PathVariable(name = "phone") String phone){
        final Client client = clientService.displayByPhone(phone);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
