package com.netcracker.controller;



import com.netcracker.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
public class BoxController {

    private  BoxService boxService;

    @DeleteMapping ("/box/delete")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = boxService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}