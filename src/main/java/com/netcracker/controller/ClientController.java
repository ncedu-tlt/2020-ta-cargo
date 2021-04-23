package com.netcracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.netcracker.model.Client;
import com.netcracker.service.ClientService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public Client create(@RequestBody Client client){
        clientService.create(client);
        return client;
    }

    @GetMapping("/client")
    public List<Client> displayAll(){
        return clientService.displayAll();
    }

    @GetMapping("/client/notnull")
    public List<Client> displayAllByLastNameNotNull(){
        return clientService.displayAllByLastNameNotNull();
    }

    @GetMapping("/client/{id}")
    public Client displayById(@PathVariable(name = "id") int id){
        return clientService.displayById(id);
    }

    @GetMapping("/client/email/{email}")
    public Client displayByEmail(@PathVariable(name = "email") String email){
        return clientService.displayByEmail(email);
    }

    @GetMapping("/client/phone/{phone}")
    public Client displayByPhone(@PathVariable(name = "phone") String phone){
        return clientService.displayByPhone(phone);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = clientService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/client")
    public ResponseEntity<?> modify(@RequestBody Client client ){
        final boolean updateField = clientService.modify(client);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
