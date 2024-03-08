package ru.mashinis.model;

public class Form {
    private int id;
    private String name;
    private String createdAt;
    private String formPath;


    /**
     * Конструктор предназначен для создания новой формы
     * при сохранении ее в БД.
     * @param name Имя формы.
     */
    public Form(String name, String formPath) {
        this.name = name;
        this.formPath = formPath;
    }

    /**
     * Конструктор предназначен для создания формы,
     * полученной из БД.
     * @param id        id формы.
     * @param name      Имя формы.
     * @param createdAt Дата создания формы.
     */
    public Form(int id, String name, String createdAt, String formPath) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.formPath = formPath;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getFormPath() {
        return formPath;
    }

    public void setFormPath(String formPath) {
        this.formPath = formPath;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", name='" + name +
                ", createdAt='" + createdAt +
                ", formPath='" + formPath +
                '}';
    }
}
