package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoxController {

    private final BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping("/box")
    public ResponseEntity<?> create(@RequestBody Box box) {
        boxService.create(box);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/box")
    public ResponseEntity<List<Box>>readeAll(){
        final List<Box> boxesList = boxService.readAll();
        return boxesList != null && !boxesList.isEmpty()
                ? new ResponseEntity<>(boxesList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/box/{id}")
    public ResponseEntity<Box> readId(@PathVariable(name = "id") int id){
        final Box box = boxService.read(id);
        return box != null
                ? new ResponseEntity<>(box, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/box/{id}")
    public ResponseEntity<?> update(@RequestBody Box box){
        final  boolean update = boxService.update(box);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/box/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = boxService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/box/patch")
    public ResponseEntity<?>  edit (@RequestBody Box box) {
        boolean edit = boxService.updatePartial(box);
        return edit
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
