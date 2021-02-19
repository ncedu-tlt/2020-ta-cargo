package com.netcracker.controller;

import com.netcracker.model.TypeCargo;
import com.netcracker.service.TypeCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeCargoController {

    private final TypeCargoService typeService;

    @Autowired
    public TypeCargoController(TypeCargoService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/type")
    public TypeCargo create(@RequestBody TypeCargo type) {
        typeService.create(type);
        return type;
    }

    @GetMapping("/type")
    public List<TypeCargo> displayAll(){
        return typeService.displayAll();
    }

    @DeleteMapping ("/type/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id){
        boolean delete = typeService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/type/{id}")
    public TypeCargo displayById(@PathVariable(name = "id") int id) {
        return typeService.displayById(id);
    }
}
