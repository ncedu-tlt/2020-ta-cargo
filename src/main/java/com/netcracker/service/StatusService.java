package com.netcracker.service;

import com.netcracker.model.Status;
import com.netcracker.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;


    public Status createStatus(Status status){
        statusRepository.save(status); // вопрос
        return status;
    }

    public Status displayById(Status status){
        return statusRepository.findById(status.getId()).get();
    }

    public Status deleteStatus (Status status){
        if (statusRepository.existsById(status.getId())){
            statusRepository.delete((status));
        }
        return status;
    }
}
