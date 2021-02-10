package com.netcracker.repository;

import com.netcracker.model.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Integer> {
    Optional<Trailer> findById(Integer id);
    Optional<Trailer> findByVolume(Integer volume);
}
