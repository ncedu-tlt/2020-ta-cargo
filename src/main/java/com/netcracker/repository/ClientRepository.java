package com.netcracker.repository;

import com.netcracker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);

    @Query("select NEW com.netcracker.model.Client(c.userId, c.lastName, c.firstName, c.middleName, c.phone, c.email, c.driveCategory, car) from Client c left join  Car car on car.client.userId = c.userId")
    List<Client> findAll();

    @Query("select NEW com.netcracker.model.Client(c.userId, c.lastName, c.firstName, c.middleName, c.phone, c.email, c.driveCategory, car) from Client c left join  Car car on car.client.userId = c.userId where c.userId = ?1")
    Optional<Client> findById(Integer id);

    @Query("select NEW com.netcracker.model.Client(c.userId, c.lastName, c.firstName, c.middleName, c.phone, c.email, c.driveCategory, car) from Client c left join  Car car on car.client.userId = c.userId where c.email = ?1")
    Optional<Client> findByEmailWithoutReturnPassword(String email);

    @Query("select NEW com.netcracker.model.Client(c.userId, c.lastName, c.firstName, c.middleName, c.phone, c.email, c.driveCategory, car) from Client c left join  Car car on car.client.userId = c.userId where c.phone = ?1")
    Optional<Client> findByPhone(String phone);

}
