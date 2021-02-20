package com.netcracker.service;

import com.netcracker.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.model.Box;

import java.util.List;


@Service
public class BoxService implements Serviceable<Box> {


    private final BoxRepository boxRepository;

    @Autowired
    public BoxService(BoxRepository boxRepository) {
        this.boxRepository = boxRepository;
    }

    @Override
    public void create(Box box) {
        boxRepository.saveAndFlush(box);
    }

    @Override
    public List<Box> displayAll() {
        return boxRepository.findAll();
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
        return boxRepository.findById(id).get();
    }

    public Box displayByClientId(Integer id){
        return boxRepository.findBoxByClient_UserId(id).get();
    }

}

