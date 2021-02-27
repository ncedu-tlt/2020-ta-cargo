package com.netcracker.controller;

import com.netcracker.model.Trailer;
import com.netcracker.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TrailerController {

    private final TrailerService trailerService;

    @Autowired
    public TrailerController(TrailerService trailerService) {
        this.trailerService = trailerService;
    }

    @PostMapping("/trailer")
    public Trailer create(@RequestBody Trailer trailer){
        trailerService.create(trailer);
        return trailer;
    }


    @GetMapping("/trailer")
    public ResponseEntity<List<Trailer>> displayAll(){
        final List<Trailer> trailerList = trailerService.displayAll();
        return trailerList != null && !trailerList.isEmpty()
                ? new ResponseEntity<>(trailerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/trailer/{id}")
    public Trailer displayById(@PathVariable(name = "id") int id){
        return trailerService.displayById(id);

    }

    @GetMapping("/trailer/volume/{volume}")
    public Trailer displayByVolume(@PathVariable(name = "volume") int volume){
        return trailerService.displayByVolume(volume);
    }

    @DeleteMapping("/trailer/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = trailerService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/trailer")
    public ResponseEntity<?> modify(@RequestBody Trailer trailer){
        final boolean updateField = trailerService.modify(trailer);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
