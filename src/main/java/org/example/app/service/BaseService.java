package org.example.app.service;

import java.util.List;
import java.util.Optional;

public interface BaseService <T1, T2>{
    boolean create(T1 request);

    List<T2> readAll();

    T2 readById(Long id);

    boolean deleteById(Long id);

    boolean updateById(Long id, T1 request);

    T2 getLastEntity();
}
