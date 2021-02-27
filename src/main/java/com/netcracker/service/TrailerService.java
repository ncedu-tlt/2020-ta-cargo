package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
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
    public Trailer create(Trailer object) {
        trailerRepository.saveAndFlush(object);
        return object;
    }

    @Override
    public List<Trailer> displayAll() {
        try {
            return trailerRepository.findAll();
        }catch (Exception ex){
            throw new SomethingNotFoundException("There aren't any Trailers");
        }
    }

    public Trailer displayById(Integer id) {
        return trailerRepository.findById(id).
           orElseThrow(() -> new SomethingNotFoundException("your Id " + id + " not found"));
    }

    public Trailer displayByVolume(Integer volume) {
        return trailerRepository.findByVolume(volume).
                orElseThrow(() -> new SomethingNotFoundException("your volume " + volume + " not found"));
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
