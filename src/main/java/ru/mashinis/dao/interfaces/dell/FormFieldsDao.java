package ru.mashinis.dao.interfaces.dell;

import java.util.List;

public interface FormFieldsDao<T> {
    List<T> getByFormId(int id);
}