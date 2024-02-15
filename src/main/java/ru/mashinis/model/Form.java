package ru.mashinis.model;

public class Form {
    private int id;
    private String formName;

    public Form(int id, String formName) {
        this.id = id;
        this.formName = formName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public String toString() {
        return id + ". " + formName;
    }
}
