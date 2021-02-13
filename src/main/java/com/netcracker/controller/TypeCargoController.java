package com.netcracker.controller;

import com.netcracker.service.TypeCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeCargoController {

    @Autowired
    private final TypeCargoService typeService;


    public TypeCargoController(TypeCargoService typeService) {
        this.typeService = typeService;
    }

    @DeleteMapping ("/type/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id){
        boolean delete = typeService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
