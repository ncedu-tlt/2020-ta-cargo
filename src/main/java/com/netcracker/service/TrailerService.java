package com.netcracker.service;

import com.netcracker.model.Trailer;
import com.netcracker.repository.TrailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrailerService implements Serviceable<Trailer>{

    private final TrailerRepository trailerRepository;

    @Autowired
    public TrailerService(TrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    @Override
    public void create(Trailer object) {
        trailerRepository.saveAndFlush(object);
    }

    @Override
    public List<Trailer> readAll() {
        return  trailerRepository.findAll();
    }

    @Override
    public Trailer readById(Integer id) {
        return trailerRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Trailer object) {
        if (trailerRepository.existsById(object.getId())) {
            trailerRepository.saveAndFlush(object);
            return true;
        }else return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (trailerRepository.existsById(id)) {
            trailerRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public boolean updatePartial(Trailer ob) {
        if (trailerRepository.existsById(ob.getId())) {
            Trailer trailerForModify = readById(ob.getId());
            trailerForModify.setName(ob.getName());
            trailerForModify.setNumber(ob.getNumber());
            trailerForModify.setVolume(ob.getVolume());
            trailerForModify.setLiftingCapacity(ob.getLiftingCapacity());
            trailerForModify.setCar(ob.getCar());
            trailerRepository.saveAndFlush(trailerForModify);
            return true;
        }else return false;
    }

}
