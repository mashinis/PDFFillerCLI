package ru.mashinis.controller;

import ru.mashinis.model.User;
import ru.mashinis.model.database.AuthModel;
import ru.mashinis.view.AuthView;

import java.util.Scanner;

public class AuthController {
    private AuthModel authModel;
    private AuthView view;
    private Scanner scanner;
    boolean isAuthenticated;
    private User authenticatedUser;  // Поле для хранения текущего аутентифицированного пользователя

    public AuthController(AuthModel authModel, AuthView authView) {
        this.authModel = authModel;
        this.view = authView;
        this.scanner = new Scanner(System.in);
        this.authenticatedUser = null;
        this.isAuthenticated = false;
    }

    public void handleUserAuthentication() {
        String choice;

        while (!isAuthenticated) {
            view.displayMenu();
            choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "\\login":
                    handleLogin();
                    break;
                case "\\register":
                    handleRegistration();
                    break;
                case "\\exit":
                    view.printMessage("Выход из приложения.");
                    scanner.close();
                    System.exit(0);
                default:
                    view.displayErrorMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void handleLogin() {
        view.promptUserLogin();
        String userLogin = scanner.nextLine();

        view.promptPassword();
        String password = scanner.nextLine();

        isAuthenticated = authModel.authenticateUser(userLogin, password);
        if (isAuthenticated) {
            authenticatedUser = authModel.getUserByLogin(userLogin);  // Получение информации о пользователе
            view.displaySuccessMessage("Аутентификация успешна! " + authenticatedUser.getUsername());
        } else {
            view.displayErrorMessage("Неверный логин или пароль. Попробуйте еще раз.");
        }
    }

    private void handleRegistration() {
        view.promptUsername();
        String username = scanner.nextLine();

        view.promptUserLogin();
        String userLogin= scanner.nextLine();

        view.promptPassword();
        String password = scanner.nextLine();
        // Нет обработки момента, если пользователь с таким логином существует
        authenticatedUser = authModel.registerUser(username, userLogin, password);
        if (authenticatedUser == null) {
            view.printMessage("Ошибка при регистрации! Повторите попытку.\n");
        } else {
            isAuthenticated = authModel.authenticateUser(userLogin, password);
            view.displaySuccessMessage("Зарегистрирован новый пользователь! " + authenticatedUser.getUsername());
        }

    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
