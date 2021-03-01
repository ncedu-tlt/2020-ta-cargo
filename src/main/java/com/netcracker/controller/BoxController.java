package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class BoxController {

    private final  BoxService boxService;

    @Autowired
    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @PatchMapping ("/box")
    public void modify(@RequestBody Box box){
        boxService.modify(box);
    }


    @PostMapping("/box")
    public Box create(@RequestBody Box box) {
       return boxService.create(box);
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

    @GetMapping("/box/{id}")
    public Box showById (@PathVariable(name = "id") int id) {
        return boxService.displayById(id);

    }

    @GetMapping("/box/byClientId/{id}")
    public List<Box> displayByClientId (@PathVariable(name = "id") Integer id) {
        return boxService.displayByClientId(id);
    }
}
