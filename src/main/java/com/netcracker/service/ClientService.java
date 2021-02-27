package com.netcracker.service;


import com.netcracker.exception.SomethingNotFoundException;
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
    public Client create(Client client) {
        clientRepository.saveAndFlush(client);
        return client;
    }

    @Override
    public List<Client> displayAll() {
        return clientRepository.findAll();
    }

    public Client displayById(Integer id) {
        return clientRepository.findById(id).
                orElseThrow(() -> new SomethingNotFoundException("Client with this id " + id + " not found"));
    }

    public Client displayByEmail(String email) {
        return clientRepository.findByEmail(email).
                orElseThrow(() -> new SomethingNotFoundException("Client with this email" + email + " not found"));
    }

    public Client displayByPhone(String phone) {
        return clientRepository.findByPhone(phone).
        orElseThrow(() -> new SomethingNotFoundException("Client with this phone " + phone + " not found"));
    }

    @Override
    public boolean delete(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }else return false;
    }

    public boolean modify(Client client) {
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
