package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.web.bind.annotation.*;



@RestController
public class BoxController {

    private  BoxService boxService;

    @PatchMapping ("/box/edit")
    public void edit(@RequestBody Box box){
        boxService.editBox(box);
    }
}