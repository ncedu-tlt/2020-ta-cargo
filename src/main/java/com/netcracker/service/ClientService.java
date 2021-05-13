package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.netcracker.model.Client;

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
            return  clientRepository.findAllWithoutPasswordAndRole();
        }catch (Exception ex){
            throw new SomethingNotFoundException("There aren't any Clients");
        }
    }

    public Client displayById(Integer id){
        return clientRepository.findById(id).get();
    }

    public Client displayByIdWithoutPasswordAndRole(Integer id) {
        return clientRepository.findByIdWithoutPasswordAndRole(id).
                orElseThrow(() -> new SomethingNotFoundException("Client with this id " + id + " not found"));
    }

    public Client displayByEmailWithoutPasswordAndRole(String email) {
        return clientRepository.findByEmailWithoutPasswordAndRole(email).
                orElseThrow(() -> new SomethingNotFoundException("Client with this email" + email + " not found"));
    }

    public Client displayByPhoneWithoutPasswordAndRole(String phone) {
        return clientRepository.findByPhoneWithoutPasswordAndRole(phone).
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
            Client clientForModify = clientRepository.findById(client.getUserId()).orElseThrow(() -> new SomethingNotFoundException("Client with this id " + client.getUserId() + " not found"));
            clientForModify.setUserId(client.getUserId());
            clientForModify.setLastName(client.getLastName());
            clientForModify.setFirstName(client.getFirstName());
            clientForModify.setMiddleName(client.getMiddleName());
            clientForModify.setPhone(client.getPhone());
            clientForModify.setEmail(client.getEmail());
            clientForModify.setDriveCategory(client.getDriveCategory());
            if (client.getPassword()!= null){
            clientForModify.setPassword(passwordEncoder.encode(client.getPassword()));
            }
            clientForModify.setRole(client.getRole());
            clientRepository.saveAndFlush(clientForModify);
            return true;
        }else  return false;
    }
}
