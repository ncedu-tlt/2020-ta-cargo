package com.netcracker.service;

import java.util.List;

public interface Serviceable<T> {

    void create(T object);
    List<T> displayAll();
    T displayById(Integer id);
    boolean delete(Integer id);
    boolean update(T object);
}


