package com.netcracker.service;


import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.netcracker.model.Client;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService implements Serviceable<Client>{

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client create(Client client) {
        clientRepository.saveAndFlush(client);
        return client;
    }

    @Override
    public List<Client> displayAll() {
        try{
            List<Client> lists = clientRepository.findAll();
            List<Client> lists2 = new ArrayList<>();
            for (Client c: lists){
                Client client = new Client();
                client.setUserId(c.getUserId());
                client.setEmail(c.getEmail());
                client.setLastName(c.getLastName());
                client.setFirstName(c.getFirstName());
                client.setMiddleName(c.getMiddleName());
                client.setPhone(c.getPhone());
                client.setDriveCategory(c.getDriveCategory());
                client.setCar(c.getCar());

                lists2.add(client);
            }
        return lists2;
    }catch (Exception ex){
        throw new SomethingNotFoundException("There aren't any Clients");
    }
    }

    public Client displayById(Integer id) {

        Client client = clientRepository.findById(id).
                orElseThrow(() -> new SomethingNotFoundException("Client with this id " + id + " not found"));

        Client client2 = new Client();
        client2.setUserId(client.getUserId());
        client2.setEmail(client.getEmail());
        client2.setLastName(client.getLastName());
        client2.setFirstName(client.getFirstName());
        client2.setMiddleName(client.getMiddleName());
        client2.setPhone(client.getPhone());
        client2.setDriveCategory(client.getDriveCategory());
        client2.setCar(client.getCar());

         return client2;
    }

    public Client displayByEmail(String email) {
        Client client = clientRepository.findByEmail(email).
                orElseThrow(() -> new SomethingNotFoundException("Client with this email" + email + " not found"));

        Client client2 = new Client();
        client2.setUserId(client.getUserId());
        client2.setEmail(client.getEmail());
        client2.setLastName(client.getLastName());
        client2.setFirstName(client.getFirstName());
        client2.setMiddleName(client.getMiddleName());
        client2.setPhone(client.getPhone());
        client2.setDriveCategory(client.getDriveCategory());
        client2.setCar(client.getCar());

        return client2;
    }

    public Client displayByPhone(String phone) {
        Client client = clientRepository.findByPhone(phone).
        orElseThrow(() -> new SomethingNotFoundException("Client with this phone " + phone + " not found"));

        Client client2 = new Client();
        client2.setUserId(client.getUserId());
        client2.setEmail(client.getEmail());
        client2.setLastName(client.getLastName());
        client2.setFirstName(client.getFirstName());
        client2.setMiddleName(client.getMiddleName());
        client2.setPhone(client.getPhone());
        client2.setDriveCategory(client.getDriveCategory());
        client2.setCar(client.getCar());

        return client2;
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
            Client clientForModify = clientRepository.findById(client.getUserId()).orElseThrow(() -> new SomethingNotFoundException("Client with this id " + client.getUserId() + " not found"));
            clientForModify.setUserId(client.getUserId());
            clientForModify.setLastName(client.getLastName());
            clientForModify.setFirstName(client.getFirstName());
            clientForModify.setMiddleName(client.getMiddleName());
            clientForModify.setPhone(client.getPhone());
            clientForModify.setEmail(client.getEmail());
            clientForModify.setDriveCategory(client.getDriveCategory());
            clientForModify.setPassword(passwordEncoder.encode(client.getPassword()));
            clientForModify.setRole(client.getRole());
            clientRepository.saveAndFlush(clientForModify);
            return true;
        }else  return false;
    }
}
