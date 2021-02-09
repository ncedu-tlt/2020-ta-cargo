package com.netcracker.controller;


import com.netcracker.model.Box;
import com.netcracker.service.BoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BoxController {

    private  BoxService boxService;

    @GetMapping("/box/showByUser")
    public List<Box> searchByUser (@RequestBody Box box) {
        return boxService.searchByUser(box);
    }
}