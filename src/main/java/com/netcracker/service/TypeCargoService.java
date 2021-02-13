package com.netcracker.service;


import com.netcracker.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeCargoService {

    @Autowired
    private final TypeRepository typeRepository;


    public TypeCargoService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
}
