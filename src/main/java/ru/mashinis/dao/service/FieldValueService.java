package ru.mashinis.dao.service;

import ru.mashinis.dao.dell.FieldValueDaoImpl;
import ru.mashinis.dao.dell.FormSubmissionsDao;
import ru.mashinis.model.FieldValue;
import ru.mashinis.model.FormSubmissions;

import java.util.List;

public class FieldValueService {
    private FormSubmissionsDao formSubmissionsDao;
    private FieldValueDaoImpl fieldValueDao;

    public FieldValueService(FormSubmissionsDao formSubmissionsDao, FieldValueDaoImpl fieldValueDao) {
        this.formSubmissionsDao = formSubmissionsDao;
        this.fieldValueDao = fieldValueDao;
    }

    public void save(List<FieldValue> fieldValueList, FormSubmissions formSubmissions) {
        int newIdentifier = formSubmissionsDao.setUserFormIdentifier(formSubmissions);
        fieldValueDao.save(fieldValueList);
    }
}
