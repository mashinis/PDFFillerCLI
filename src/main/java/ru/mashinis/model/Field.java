package ru.mashinis.model;

public class Field {
    private int id;
    private String name;
    private String alias;
    private int formId;

    public Field(String name, String alias, int formId) {
        this.name = name;
        this.alias = alias;
        this.formId = formId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id='" + id +
                "', name='" + name +
                "', alias='" + alias +
                "', formId='" + formId +
                "'}";
    }
}
