package ru.mashinis.controller;

import ru.mashinis.model.User;
import ru.mashinis.model.database.UserModel;
import ru.mashinis.view.UserView;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserView view;
    private final UserModel userModel;

    public UserController(UserView view, UserModel userModel) {
        this.view = view;
        this.userModel = userModel;
    }

    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.printMenu();
            String input = scanner.nextLine();
            String[] s = input.toLowerCase().split(" ");
            String choice = s[0].trim();

            switch (choice.toLowerCase()) {
                case "\\menu":
                    return;  // Возврат в главное меню
                case "\\reg": // Регистрация нового пользователя
                    saveUser();
                    break;
                case "\\all": // Вывод всех пользователей
                    displayAllUsers();
                    break;
                case "\\user": // Вывод пользователя по его id
                    displayUser(s[1].trim());
                    break;
                case "\\exit":
                    scanner.close();
                    System.exit(0);
                default:
                    view.printMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void displayUser(String input) {

        if (input.isEmpty()) {
            view.printMessage("Не верный формат строки.");
            return;
        }

        int userId = Integer.parseInt(input);
        User user = userModel.getUserById(userId);

        if (user == null) {
            view.printMessage("Нет пользователя с указанным id.");
        } else {
            view.printMessage("Найден пользователь с id " + userId + ":");
            view.printMessage(user.toString());
        }
    }

    private void saveUser() {
        Scanner scanner = new Scanner(System.in);

        view.printMessage("Введите имя пользователя:");
        String username = scanner.nextLine();

        view.printMessage("Введите логин:");
        String login = scanner.nextLine();

        view.printMessage("Введите пароль:");
        String password = scanner.nextLine();

        User user = new User(username, login, password);
        userModel.saveUser(user);
        view.printMessage("Пользователь успешно сохранен.");
    }

    private void displayAllUsers() {
        List<User> users = userModel.getAllUsers();
        if (users.isEmpty()) {
            view.printMessage("Нет пользователей в базе данных.");
        } else {
            view.printMessage("Список пользователей:");
            for (User user : users) {
                view.printMessage(user.toString());
            }
        }
    }

    // Вход пользователя
    public void loginUser() {
        Scanner scanner = new Scanner(System.in);

        view.printMessage("Введите логин:");
        String login = scanner.nextLine();

        view.printMessage("Введите пароль:");
        String password = scanner.nextLine();

        if (validateUserCredentials(login, password)) {
            view.printMessage("Вход выполнен успешно!");
        } else {
            view.printMessage("Неверные логин или пароль. Попробуйте еще раз.");
        }
    }

    private boolean validateUserCredentials(String login, String password) {
        // Здесь вы можете добавить логику проверки логина и пароля в вашей базе данных
        // Например, использовать метод из UserModel для проверки существования пользователя
        // и сопоставления введенного пароля с хэшированным паролем в базе данных.
        return userModel.validateUserCredentials(login, password);
    }
}
