package com.netcracker.repository;

import com.netcracker.model.Box;
import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BoxRepository extends JpaRepository<Box, Integer> {
    Optional<Box> findBoxByClientId(Client client);
}