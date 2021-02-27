package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
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

    public Status displayById(int id){
        return statusRepository.findById(id).
                orElseThrow(() -> new SomethingNotFoundException("your Id " + id + " not found"));
    }

    public Status deleteStatus (Status status){
        if (statusRepository.existsById(status.getId())){
            statusRepository.delete((status));
        }
        return status;
    }
}
