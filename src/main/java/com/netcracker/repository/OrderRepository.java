package com.netcracker.repository;


import com.netcracker.model.Order;
import com.netcracker.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findOrderByLocation_City (String city);

    List<Order> findOrderByReceiverUserId (Integer id);

    List<Order> findOrderByBoxClientUserId(Integer id);

    List<Order> findOrderByStatusName(String name);

    @Query("select o from Order o where o.box.client.userId = :id and o.status.name = :status")
    List<Order> findOrderByBoxClientIdAndStatus(@Param("id") Integer id, @Param("status") String status);

    @Query("select o from Order o where o.box.client.userId = :id and o.status.name <> :status")
    List<Order> findOrderByBoxClientIdAndNotStatus(@Param("id") Integer id, @Param("status") String status);

    @Query("select o from Order o where o.driver.userId = :id and o.status.name = :status")
    List<Order> findOrderByDriverIdAndStatus(@Param("id") Integer id, @Param("status") String status);
}

