package com.netcracker.service;


import com.netcracker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netcracker.model.Client;

import java.util.List;


@Service
public class ClientService implements Serviceable<Client>{

    @Autowired
    private  final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Client client) {
        clientRepository.saveAndFlush(client);
    }

    @Override
    public List<Client> displayAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client displayById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client displayByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    public Client displayByPhone(String phone) {
        return clientRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public boolean update(Client client) {
        if (clientRepository.existsById(client.getUserId())) {
            Client clientForModify = displayById(client.getUserId());
            clientForModify.setUserId(client.getUserId());
            clientForModify.setLastName(client.getLastName());
            clientForModify.setFirstName(client.getFirstName());
            clientForModify.setMiddleName(client.getMiddleName());
            clientForModify.setPhone(client.getPhone());
            clientForModify.setEmail(client.getEmail());
            clientForModify.setDriveCategory(client.getDriveCategory());
            clientRepository.saveAndFlush(clientForModify);
            return true;
        }else  return false;
    }
}
