package ru.mashinis.model;

/**
 * Класс, представляющий пользователя.
 */
public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String patronymic;
    private String secondName;

    /**
     * Конструктор для создания нового пользователя без известного id.
     *
     * @param email      Электронная почта пользователя.
     * @param password   Пароль пользователя.
     * @param firstName  Имя пользователя.
     * @param patronymic Отчество пользователя.
     * @param secondName Фамилия пользователя.
     */
    public User(String email, String password, String firstName, String patronymic, String secondName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.secondName = secondName;
    }

    /**
     * Конструктор для создания пользователя с известным id.
     *
     * @param id         Идентификатор пользователя.
     * @param email      Электронная почта пользователя.
     * @param password   Пароль пользователя.
     * @param firstName  Имя пользователя.
     * @param patronymic Отчество пользователя.
     * @param secondName Фамилия пользователя.
     */
    public User(int id, String email, String password, String firstName, String patronymic, String secondName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.secondName = secondName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", second_name='" + secondName + '\'' +
                '}';
    }
}