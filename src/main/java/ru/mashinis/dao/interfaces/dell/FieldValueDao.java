package ru.mashinis.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface FieldValueDao<T> {
    Optional<T> getById(int id);
    List<T> getAll(int identifier);
    void save(List<T> t);
    void update(T t, String[] params);
    void delete(T t);
}
