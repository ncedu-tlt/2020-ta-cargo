package com.netcracker.service;

import com.netcracker.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.model.Box;

import java.util.List;


@Service
public class BoxService implements Serviceable<Box>{

    @Autowired
    private BoxRepository boxRepository;

    @Override
    public void create(Box box) {
        boxRepository.save(box);
    }
    @Override
    public List<Box> displayAll() {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
