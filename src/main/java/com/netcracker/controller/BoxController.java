package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoxController {

    private  BoxService boxService;

    @PostMapping ("/box/delete")
    public Box delete (@RequestBody Box box){
        boxService.deleteBox(box);
        return box;
    }


}