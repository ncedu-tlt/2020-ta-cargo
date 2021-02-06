package com.netcracker.controller;

import com.netcracker.model.Trailer;
import com.netcracker.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
