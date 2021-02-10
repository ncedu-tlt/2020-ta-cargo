package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoxController {

    private  BoxService boxService;

    @PatchMapping ("/box/edit")
    public void edit(@RequestBody Box box){
        boxService.editBox(box);
    }
    @PostMapping("/box/create")
    public Box create(@RequestBody Box box) {
        boxService.create(box);
        return box;
    }

    @DeleteMapping ("/box/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = boxService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping ("/box/showAll")
    public List<Box> showAll(){
        return boxService.displayAll();
    }
    @GetMapping("/box/showById")
    public Box showById (@RequestBody Box box) {
        return boxService.searchById(box);
    }
    @GetMapping("/box/showByUser")
    public List<Box> searchByUser (@RequestBody Box box) {
        return boxService.searchByUser(box);
    }
}