package rest.service;

import org.springframework.stereotype.Service;
import rest.model.Client;

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
    public void create(Client object) {
        final int id = CLIENT_ID_HOLDER.incrementAndGet();
        object.setUserId(id);
        CLIENT_MAP.put(id,object);
    }

    @Override
    public List<Client> readAll() {
        List<Client> clientList = new ArrayList<>(CLIENT_MAP.values());
        return clientList;
    }

    @Override
    public Client read(int id) {
        Client client = CLIENT_MAP.get(id);
        return client;
    }

    @Override
    public boolean update(int id, Client object) {
        if (CLIENT_MAP.containsKey(id)) {
            object.setUserId(id);
            CLIENT_MAP.put(id, object);
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
}
