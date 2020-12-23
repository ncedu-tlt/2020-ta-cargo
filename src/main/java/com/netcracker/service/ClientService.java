package com.netcracker.service;

import org.springframework.stereotype.Service;
import com.netcracker.model.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientService implements Serviceable<Client>{

    private static final Map<Integer, Client> CLIENT_MAP = new HashMap<>();
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Client client) {
        final int id = CLIENT_ID_HOLDER.incrementAndGet();
        client.setUserId(id);
        CLIENT_MAP.put(id,client);
    }

    @Override
    public List<Client> readAll() {
               return  new ArrayList<>(CLIENT_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_MAP.get(id);
    }

    @Override
    public boolean update(Client client) {
        if (CLIENT_MAP.containsKey(client.getUserId())) {
            CLIENT_MAP.put(client.getUserId(), client);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (CLIENT_MAP.containsKey(id)) {
            CLIENT_MAP.remove(id);
            return true;
        }else return false;
    }

    @Override
    public boolean updatePartial(Client client){
        if (CLIENT_MAP.containsKey(client.getUserId())) {
            Client clientForModify = read(client.getUserId());
            clientForModify.setUserId(client.getUserId());
            clientForModify.setLastName(client.getLastName());
            clientForModify.setFirstName(client.getFirstName());
            clientForModify.setMiddleName(client.getMiddleName());
            clientForModify.setPhone(client.getPhone());
            clientForModify.setEmail(client.getEmail());
            clientForModify.setDriveCategory(client.getDriveCategory());
            CLIENT_MAP.put(clientForModify.getUserId(), clientForModify);
                return true;
            }else return  false;
        }

}
