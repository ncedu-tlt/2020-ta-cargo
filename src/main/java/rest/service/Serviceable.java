package rest.service;

import java.util.List;

public interface Serviceable<T> {

    void create(T object);
    List<T> readAll();
    T read(int id);
    boolean update(int id, T object);
    boolean delete(int id);

}
