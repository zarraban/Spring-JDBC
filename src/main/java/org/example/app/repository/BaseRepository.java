package org.example.app.repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BaseRepository <T, T2>{
    boolean create(T entity);

    Optional<List<T2>> read();

    Optional<T2> readById(Long id);

    boolean updateById(Long id, T entity);

    boolean deleteById(Long id);

    Optional<T2> getLastEntity();

}
