package ru.mashinis.controller;

import ru.mashinis.dao.implement.UserDao;
import ru.mashinis.dbmanager.DatabaseManager;
import ru.mashinis.model.User;
import ru.mashinis.dao.service.UserService;

import java.util.Scanner;

public class AuthController {
    private Scanner scanner;
    private boolean isAuthentication;
    private User user;

    public AuthController(Scanner scanner) {
        this.scanner = scanner;
        this.isAuthentication = false;
        this.user = null;
    }

    public User start() {
        while (!isAuthentication) {
            System.out.println("Для работы с системой требуется аутентификации.\r\nВыберите действие:\r\n" +
                    "\\login - Войти в систему\r\n" +
                    "\\register - Зарегистрировать нового пользователя\r\n" +
                    "\\exit - Выйти из приложения."
            );

            String choice = scanner.nextLine();

            switch (choice.trim().toLowerCase()) {
                case "\\login":
                    authenticate();
                    return user;
                case "\\register":
                    register();
                    return user;
                case "\\exit":
                    System.out.println("Выход из приложения.");
                    System.exit(0);
                default:
                    System.out.println("Неверная команда. Попробуйте еще раз.");
            }
        }
        return user;
    }

    private void authenticate() {
        UserDao userDao = new UserDao(DatabaseManager.getDataSource());
        UserService userService = new UserService(userDao); // Нужен для валидации полей пользователя
        String email;
        String password;

        while (true) {
            System.out.print("Введите email: ");
            email = scanner.nextLine();
            System.out.print("Введите пароль: ");
            password = scanner.nextLine();

            User user = userService.authenticateUser(email, password);
            if (user != null) {
                System.out.println(user.getFirstName() + ", Добро пожаловать!");
                isAuthentication = true;
                this.user = user;
                return;
            } else {
                System.out.println("Неверный email или пароль!\r\nПовторите ввод.");
            }
        }
    }

    private void register() {
        UserDao userDao = new UserDao(DatabaseManager.getDataSource());
        UserService userService = new UserService(userDao); // Нужен для валидации полей пользователя
        String email;
        String password;

        while (true) {
            System.out.print("Введите email: ");
            email = scanner.nextLine();

            if (userService.isEmailUnique(email)) { // Если почта уникальная
                break;
            }

            System.out.println("Пользователь с таким email уже существует");
        }

        while (true) {
            System.out.print("Введите пароль: ");
            password = scanner.nextLine();

            if (userService.isPasswordSecure(password)) { // если пароль сложный
                break;
            }

            System.out.println("Пароль слишком легкий. Должен быть не менее 4 символов");
        }

        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите отчество: ");
        String patronymic = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String secondName = scanner.nextLine();

        User newUser = new User(email, password, firstName, patronymic, secondName);

        if (userService.registerUser(newUser)) {
            System.out.println(newUser.getFirstName() + ", Вы успешно прошли регистрацию!");
            isAuthentication = true;
            this.user = newUser;
        } else {
            System.err.println("Ошибка при регистрации!");
            this.user = null;
        }
    }
}
