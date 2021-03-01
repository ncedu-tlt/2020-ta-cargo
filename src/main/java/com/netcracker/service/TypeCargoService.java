package com.netcracker.service;


import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.model.Box;
import com.netcracker.model.TypeCargo;
import com.netcracker.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCargoService implements Serviceable<TypeCargo>{


    private final TypeRepository typeRepository;

    @Autowired
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
        try{
        return typeRepository.findAll();
        }catch (Exception ex){
            throw new SomethingNotFoundException("There aren't any Types");
        }
    }

    @Override
    public boolean delete(Integer id) {
        if(typeRepository.existsById(id)){
            typeRepository.deleteById(id);
            return true;
        }else return false;
    }

    public TypeCargo displayById(Integer id) {
        return typeRepository.findById(id).get();
    }
}
