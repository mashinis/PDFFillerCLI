package ru.mashinis.model;

public class User {
    private int userId;
    private String username;
    private String login;
    private String password;

    public User(String username, String login, String password) {
        this.username = username;
        this.login = login;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{userId='" + userId + "',username='" + username + "',login='" + login + "', password='" + password + "'}";
    }
}