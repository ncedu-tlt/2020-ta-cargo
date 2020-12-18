package com.netcracker.service;

import org.springframework.stereotype.Service;
import com.netcracker.model.Box;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BoxService implements Serviceable<Box>{
    private static final Map<Integer, Box> BOX_MAP = new HashMap<>();
    private static final AtomicInteger BOX_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Box box) {
        final int id = BOX_ID_HOLDER.incrementAndGet();
        box.setBoxId(id);
        BOX_MAP.put(id, box);
    }

    @Override
    public List<Box> readAll() {
        List<Box> boxesList = new ArrayList<>(BOX_MAP.values());
        return boxesList;
    }

    @Override
    public Box read(int id) {
        Box box = BOX_MAP.get(id);
        return box;
    }

    @Override
    public boolean update(int id, Box object) {
        if (BOX_MAP.containsKey(id)) {
            object.setBoxId(id);
            BOX_MAP.put(id, object);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (BOX_MAP.containsKey(id)) {
            BOX_MAP.remove(id);
            return true;
        }else return false;
    }
}
