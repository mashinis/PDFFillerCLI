package ru.mashinis.model;

/**
 * Объекты этого класса будут хранить JSON объекты
 */
public class ContentFields {
    private int field_id;
    private String field_name;
    private String field_alias;
    private String value;

    public ContentFields(int field_id, String field_name, String field_alias, String value) {
        this.field_id = field_id;
        this.field_name = field_name;
        this.field_alias = field_alias;
        this.value = value;
    }

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_alias() {
        return field_alias;
    }

    public void setField_alias(String field_alias) {
        this.field_alias = field_alias;
    }

    public String getContent() {
        return value;
    }

    public void setContent(String content) {
        this.value = value;
    }
}
