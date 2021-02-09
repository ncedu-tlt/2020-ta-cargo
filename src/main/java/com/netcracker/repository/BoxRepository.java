package com.netcracker.repository;

import com.netcracker.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoxRepository extends JpaRepository<Box, Long> {

    List<Box> findBoxByClientId(Integer clientId);
}