package com.netcracker.controller;

import com.netcracker.model.Status;
import com.netcracker.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/status/{id}")
    public Status displayById(@PathVariable (name = "id") int id) {
        return statusService.displayById(id);
    }

    @PostMapping ("/status")
    public Status createStatus (@RequestBody Status status){
        statusService.createStatus (status);
        return  status;
    }
}
