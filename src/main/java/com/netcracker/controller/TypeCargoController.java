package com.netcracker.controller;

import com.netcracker.model.TypeCargo;
import com.netcracker.service.TypeCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeCargoController {

    @Autowired
    private final TypeCargoService typeService;


    public TypeCargoController(TypeCargoService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/type/showAll")
    public List<TypeCargo> showAll(){
        return typeService.displayAll();
    }

    @DeleteMapping ("/type/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id){
        boolean delete = typeService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
