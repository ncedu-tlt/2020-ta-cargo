package com.netcracker.controller;

import com.netcracker.service.TypeCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeCargoController {

    @Autowired
    private final TypeCargoService typeService;


    public TypeCargoController(TypeCargoService typeService) {
        this.typeService = typeService;
    }
}
