package ru.mashinis.dao.interfaces;

import java.util.Optional;

public interface Read<T> {
    Optional<T> getById(int id);
}
