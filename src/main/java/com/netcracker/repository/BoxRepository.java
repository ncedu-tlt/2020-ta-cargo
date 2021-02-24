package com.netcracker.repository;

import com.netcracker.model.Box;
import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {
    Optional<Box> findBoxByClient_UserId(Integer id);
}
