package ru.mashinis.model;

public class FormFields {
    private int id;
    private int formId;
    private int fieldId;

    public FormFields(int id, int formId, int fieldId) {
        this.id =id;
        this.formId = formId;
        this.fieldId = fieldId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
}
