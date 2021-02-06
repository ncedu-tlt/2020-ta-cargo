package com.netcracker.service;

import java.util.List;

public interface Serviceable<T> {

    void create(T object);
    List<T> displayAll();
    boolean delete(Integer id);
    // boolean modify(T ob);
    // T displayById(Integer id);
    // boolean update(T object);
}


