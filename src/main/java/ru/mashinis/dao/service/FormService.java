package ru.mashinis.dao.service;

import ru.mashinis.dao.implement.FieldDao;
import ru.mashinis.dao.implement.FormDao;
import ru.mashinis.model.Field;
import ru.mashinis.model.Form;
import ru.mashinis.pdfgeneration.CyrillicPdfFormFiller;

import java.util.List;

/**
 * Сервис связывает добавление записи в таблицу форм и таблицу полей формы
 */
public class FormService {
    private FormDao formDao;
    private FieldDao fieldDao;

    public FormService(FormDao formDao, FieldDao fieldDao) {
        this.formDao = formDao;
        this.fieldDao = fieldDao;
    }

    public int createForm(Form form) {
        int formId = formDao.create(form);
        CyrillicPdfFormFiller cyrillicPdfFormFiller = new CyrillicPdfFormFiller(form.getFormPath());
        List<Field> fields = cyrillicPdfFormFiller.readFormAndSaveFields(formId);
        fieldDao.create(fields, formId);

        return formId;
    }
}
