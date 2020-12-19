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
        List<Client> clientList = new ArrayList<>(CLIENT_MAP.values());
        return clientList;
    }

    @Override
    public Client read(int id) {
        Client client = CLIENT_MAP.get(id);
        return client;
    }

    @Override
    public boolean update(int id, Client client) {
        if (CLIENT_MAP.containsKey(id)) {
            client.setUserId(id);
            CLIENT_MAP.put(id, client);
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
    public boolean updatePartial(int id, Client client){
        if (CLIENT_MAP.containsKey(id)) {
            client.setUserId(id);
            Client clientForModify = read(id);
            clientForModify.setUserId(id);

            if((client.getLastName() != null)&&(!client.getLastName().isEmpty())) {
                clientForModify.setLastName(client.getLastName());
            }else if((client.getFirstName() != null)&&(!client.getFirstName().isEmpty())) {
                clientForModify.setFirstName(client.getFirstName());
            }else if((client.getMiddleName() != null)&&(!client.getMiddleName().isEmpty())) {
                clientForModify.setMiddleName(client.getMiddleName());
            }else if((client.getPhone() != null)&&(!client.getPhone().isEmpty())) {
                clientForModify.setPhone(client.getPhone());
            }else if((client.getEmail() != null)&&(!client.getEmail().isEmpty())) {
                clientForModify.setEmail(client.getEmail());
            }else if((client.getDriveCategory() != null)&&(!client.getDriveCategory().isEmpty())) {
                clientForModify.setDriveCategory(client.getDriveCategory());
            }else return false;

            CLIENT_MAP.put(id,clientForModify);
                return true;
            }else return  false;
        }

}
