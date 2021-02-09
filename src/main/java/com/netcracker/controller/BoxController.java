package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoxController {

    private  BoxService boxService;

    @GetMapping ("/box/showAll")
    public List<Box> showAll(){
        return boxService.displayAll();
    }
}