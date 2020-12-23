package com.netcracker.service;

import java.util.List;

public interface Serviceable<T> {

    void create(T object);
    List<T> readAll();
    T read(int id);
    boolean update(T object);
    boolean delete(int id);
    boolean updatePartial(T ob);
}
