package ru.mashinis.model;

/**
 * Объекты этого класса будут хранить JSON объекты
 */
public class ContentFieldJson {
    private int fieldId;
    private String fieldName;
    private String fieldAlias;
    private String value;

    public ContentFieldJson(int fieldId, String fieldName, String fieldAlias, String value) {
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldAlias = fieldAlias;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return fieldId + ". " + fieldAlias + " - " + value;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }
}