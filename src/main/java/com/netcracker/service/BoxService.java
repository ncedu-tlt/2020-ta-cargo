//package com.netcracker.service;
//
//import org.springframework.stereotype.Service;
//import com.netcracker.model.Box;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Service
//public class BoxService  implements Serviceable<Box>{
//    private static final Map<Integer, Box> BOX_MAP = new HashMap<>();
//    private static final AtomicInteger BOX_ID_HOLDER = new AtomicInteger();
//
//    @Override
//    public void create(Box box) {
//        final int id = BOX_ID_HOLDER.incrementAndGet();
//        box.setBoxId(id);
//        BOX_MAP.put(id, box);
//    }
//
//
//    @Override
//    public List<Box> readAll() {
//        return new ArrayList<>(BOX_MAP.values());
//    }
//
//    @Override
//    public Box read(int id) {
//        return BOX_MAP.get(id);
//    }
//
//    @Override
//    public boolean update(Box box) {
//        if (BOX_MAP.containsKey(box.getBoxId())) {
//            BOX_MAP.put(box.getBoxId(), box);
//            return true;
//        }
//        return false;
//    }
//
//
//    @Override
//    public boolean delete(int id) {
//        if (BOX_MAP.containsKey(id)) {
//            BOX_MAP.remove(id);
//            return true;
//        }else return false;
//    }
//
//
//
//    public boolean updatePartial(Box box) {
//        int id = box.getBoxId();
//
//        if (BOX_MAP.containsKey(id)) {
//           Box boxModify = read(box.getBoxId());
//            boxModify.setName(box.getName());
//            boxModify.setWidth(box.getWidth());
//            boxModify.setHeight(box.getHeight());
//            boxModify.setVolume(box.getVolume());
//            boxModify.setWeight(box.getWeight());
//            boxModify.setTypeId(box.getTypeId());
//            boxModify.setClientId(box.getClientId());
//            BOX_MAP.put(boxModify.getBoxId(), boxModify);
//            return true;
//        }else{
//            return false;
//        }
//    }
//}
