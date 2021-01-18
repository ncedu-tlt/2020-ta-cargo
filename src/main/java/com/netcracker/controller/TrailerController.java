package com.netcracker.controller;

import com.netcracker.model.Address;
import com.netcracker.model.Trailer;
import com.netcracker.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrailerController {

    private final TrailerService trailerService;

    @Autowired
    public TrailerController(TrailerService trailerService) {
        this.trailerService = trailerService;
    }

    @PostMapping("/trailer")
    public ResponseEntity<?> create(@RequestBody Trailer trailer){
        trailerService.create(trailer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/trailer")
    public ResponseEntity<List<Trailer>>readeAll(){
        final List<Trailer> trailerList = trailerService.readAll();
        return trailerList != null && !trailerList.isEmpty()
                ? new ResponseEntity<>(trailerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/trailer/{id}")
    public ResponseEntity<Trailer> readById(@PathVariable(name = "id") int id){
        final Trailer trailer = trailerService.readById(id);
        return trailer != null
                ? new ResponseEntity<>(trailer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/trailer")
    public ResponseEntity<?> update(@RequestBody Trailer trailer){
        final  boolean update = trailerService.update(trailer);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping("/trailer/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = trailerService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @PatchMapping("/trailer")
    public ResponseEntity<?> updatePartial(@RequestBody Trailer trailer){
        final boolean updateField = trailerService.updatePartial(trailer);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
