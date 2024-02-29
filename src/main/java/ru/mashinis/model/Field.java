package ru.mashinis.model;

public class Field {
    private int fieldId;
    private int formId;
    private String fieldName;
    private String fieldAlias;
    private String createdAt;
    public Field(int formId, String fieldName, String fieldAlias) {
        this.formId = formId;
        this.fieldName = fieldName;
        this.fieldAlias = fieldAlias;
    }

    public Field(int fieldId, int formId, String fieldName, String fieldAlias, String createdAt) {
        this.fieldId = fieldId;
        this.formId = formId;
        this.fieldName = fieldName;
        this.fieldAlias = fieldAlias;
        this.createdAt = createdAt;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }

    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId=" + fieldId +
                ", formId=" + formId +
                ", fieldName='" + fieldName + '\'' +
                ", fieldAlias='" + fieldAlias + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
