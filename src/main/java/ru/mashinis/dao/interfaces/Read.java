package ru.mashinis.dao.interfaces;

import java.util.List;

public interface ReadOperation<T> {
    T getById(int id);
    List<T> getAll();
}
