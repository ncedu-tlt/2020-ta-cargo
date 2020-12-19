package rest.service;

import org.springframework.stereotype.Service;
import rest.model.Address;
import rest.model.Client;

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
        object.setLocationId(id);
        ADDRESS_MAP.put(id, object);
    }

    @Override
    public List<Address> readAll() {
        List<Address> addressList = new ArrayList<>(ADDRESS_MAP.values());
        return addressList;
    }

    @Override
    public Address read(int id) {
        Address address = ADDRESS_MAP.get(id);
        return address;
    }

    @Override
    public boolean update(int id, Address object) {
        if (ADDRESS_MAP.containsKey(id)) {
            object.setLocationId(id);
            ADDRESS_MAP.put(id, object);
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
    public boolean updatePartial(int id, Address address){
        if (ADDRESS_MAP.containsKey(id)) {
            Address addressForModify = read(id);
            addressForModify.setLocationId(id);

            if((address.getCountry() != null)&&(!address.getCountry().isEmpty())) {
                addressForModify.setCountry(address.getCountry());
            }else if((address.getCountry() != null)&&(!address.getCity().isEmpty())){
                addressForModify.setCity(address.getCity());
            }else if((address.getStreet() != null)&&(!address.getStreet().isEmpty())){
                addressForModify.setStreet(address.getStreet());
            }else if((address.getHome() != null)&&(!address.getHome().isEmpty())){
                addressForModify.setHome(address.getHome());
            }else if((address.getApartment() != null)&&(!address.getApartment().isEmpty())) {
                addressForModify.setApartment(address.getApartment());
            }else return false;

            ADDRESS_MAP.put(id, addressForModify);
            return true;
        }else return  false;
    }
}
