package com.netcracker.service;

import java.util.List;

public interface Serviceable<T> {
    void create(T object);
    List<T> displayAll();
    boolean delete(Integer id);
}


