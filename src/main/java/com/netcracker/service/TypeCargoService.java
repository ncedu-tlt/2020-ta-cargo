package com.netcracker.service;


import com.netcracker.model.Box;
import com.netcracker.model.TypeCargo;
import com.netcracker.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCargoService implements Serviceable<TypeCargo>{

    @Autowired
    private final TypeRepository typeRepository;


    public TypeCargoService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public TypeCargo create(TypeCargo type) {
        typeRepository.save(type);
        return type;
    }

    @Override
    public List<TypeCargo> displayAll() {
        return typeRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if(typeRepository.existsById(id)){
            typeRepository.deleteById(id);
            return true;
        }else return false;
    }

    public TypeCargo searchById (TypeCargo typeCargo) {
        return typeRepository.findById(typeCargo.getTypeId()).get();
    }
}
