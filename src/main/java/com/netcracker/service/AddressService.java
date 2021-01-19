package com.netcracker.service;

import org.springframework.stereotype.Service;
import com.netcracker.model.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AddressService  implements Serviceable<Address>{

    private static final Map<Integer, Address> ADDRESS_MAP = new HashMap<>();
    private static final AtomicInteger ADDRESS_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Address object) {
        final int id = ADDRESS_ID_HOLDER.incrementAndGet();
        object.setAddressId(id);
        ADDRESS_MAP.put(id, object);
    }

    @Override
    public List<Address> readAll() {
        return new ArrayList<>(ADDRESS_MAP.values());
    }

    @Override
    public Address read(int id) {
        return  ADDRESS_MAP.get(id);
    }

    @Override
    public boolean update(Address object) {
        if (ADDRESS_MAP.containsKey(object.getAddressId())) {
            ADDRESS_MAP.put(object.getAddressId(), object);
            return true;
        } else return false;

    }

    @Override
    public boolean delete(int id) {
        if (ADDRESS_MAP.containsKey(id)) {
            ADDRESS_MAP.remove(id);
            return true;
        }else return false;
    }

    @Override
    public boolean updatePartial(Address address){
        if (ADDRESS_MAP.containsKey(address.getAddressId())) {
            Address addressForModify = read(address.getAddressId());
            addressForModify.setAddressId(address.getAddressId());
            addressForModify.setCountry(address.getCountry());
            addressForModify.setCity(address.getCity());
            addressForModify.setStreet(address.getStreet());
            addressForModify.setHome(address.getHome());
            addressForModify.setApartment(address.getApartment());
            ADDRESS_MAP.put(addressForModify.getAddressId(), addressForModify);
            return true;
        }else return  false;
    }
}
