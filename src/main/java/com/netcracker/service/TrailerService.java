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
    public List<Trailer> displayAll() {
        return  trailerRepository.findAll();
    }

    public Trailer displayById(Integer id) {
        return trailerRepository.findById(id).get();
    }

    public Trailer displayByVolume(Integer volume) {
        return trailerRepository.findByVolume(volume).get();
    }

    @Override
    public boolean delete(Integer id) {
        if (trailerRepository.existsById(id)) {
            trailerRepository.deleteById(id);
            return true;
        }else return false;
    }

    public boolean modify(Trailer ob) {
        if (trailerRepository.existsById(ob.getId())) {
            Trailer trailerForModify = displayById(ob.getId());
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
