package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.model.Client;
import com.netcracker.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.model.Box;

import java.util.List;
import java.util.Optional;


@Service
public class BoxService implements Serviceable<Box> {


    private final BoxRepository boxRepository;

    @Autowired
    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    @Override
    public Box create(Box box) {
        boxRepository.saveAndFlush(box);
        return box;
    }

    @Override
    public List<Box> displayAll() {
        try {
        return boxRepository.findAll();
        }catch (Exception ex){
            throw new SomethingNotFoundException("There aren't any Boxes");
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (boxRepository.existsById(id)) {
            boxRepository.deleteById(id);
            return true;
        } else return false;
    }

    public boolean modify(Box box) {
        if (boxRepository.existsById(box.getBoxId())) {
            Box boxForModify = displayById(box.getBoxId());
            boxForModify.setName(box.getName());
            boxForModify.setHeight(box.getHeight());
            boxForModify.setWeight(box.getWeight());
            boxForModify.setWidth(box.getWidth());
            boxForModify.setVolume(box.getVolume());
            boxForModify.setTypeCargo(box.getTypeCargo());
            boxForModify.setClient(box.getClient());
            boxRepository.saveAndFlush(boxForModify);
            return true;
        } else return false;
    }

    public Box displayById(Integer id) {
        return boxRepository.findById(id).
                orElseThrow(() -> new SomethingNotFoundException("The Box " + id + " not found"));
    }

    public List<Box> displayByClientId(Integer id){
        return boxRepository.findBoxByClientUserId(id).
                orElseThrow(() -> new SomethingNotFoundException("The Client with Id " + id + " doesn't have any box"));
    }
}

