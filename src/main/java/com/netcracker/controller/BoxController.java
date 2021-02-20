package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.model.Client;
import com.netcracker.service.BoxService;
import com.netcracker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoxController {

    private final  BoxService boxService;
    private  final ClientService clientService;

    @Autowired
    public BoxController(BoxService boxService, ClientService clientService) {
        this.boxService = boxService;
        this.clientService = clientService;
    }

    @PatchMapping ("/box")
    public void modify(@RequestBody Box box){
        boxService.modify(box);
    }


    @PostMapping("/box")
    public void create(@RequestBody Box box) {
        boxService.create(box);
        return box;
    }

    @DeleteMapping ("/box/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = boxService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping ("/box")
    public List<Box> showAll(){
        return boxService.displayAll();
    }

    @GetMapping("/box/showById/{id}")
    public ResponseEntity<Box> showById (@PathVariable(name = "id") int id) {
        Box box = boxService.displayById(id);
        return box != null
                ? new ResponseEntity<>(box, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/box/ClientId/{id}")
    public ResponseEntity<Box> displayByClientId (@PathVariable(name = "id") Integer id) {
        Client client = clientService.displayById(id);
        Box box = boxService.displayByClientId(client);
        return box != null
                ? new ResponseEntity<>(box, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}