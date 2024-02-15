package ru.mashinis.controller;

import ru.mashinis.model.FormField;
import ru.mashinis.model.InputModel;
import ru.mashinis.model.database.AuthModel;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.model.database.UserModel;
import ru.mashinis.view.AuthView;
import ru.mashinis.view.FieldView;
import ru.mashinis.view.InputView;
import ru.mashinis.view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Контроллер, управляющий взаимодействием между InputModel и InputView
 */
public class InputController {
    private InputModel model;
    private InputView view;
    private Scanner scanner;
    private List<FormField> formFields;
    private UserView userView;
    private UserModel userModel;
    private UserController userController;
    private FieldView fieldView;
    private FieldModel fieldModel;
    private FieldController fieldController;
    private AuthController authController;
    private AuthModel authModel;
    private AuthView authView;

    public InputController(InputModel model, InputView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.formFields = new ArrayList<>();
        this.userView = new UserView();
        this.userModel = new UserModel();
        this.fieldView = new FieldView();
        this.fieldModel = new FieldModel();
        this.authModel = new AuthModel();
        this.authView = new AuthView();
    }

    // Метод обработки ввода пользователя в бесконечном цикле
    public void start() {
        Scanner scanner = new Scanner(System.in);

        authController = new AuthController(authModel, authView);

        while (true) {
            authController.handleUserAuthentication();

            if (authController.isAuthenticated()) {
                // Если аутентификация успешна, переходим к главному меню
                handleMainMenu();
            } else {
                // В противном случае, возвращаемся к аутентификации
                authView.displayErrorMessage("Неверный логин или пароль. Попробуйте еще раз.");
            }
        }
    }

    private void handleMainMenu() {
        while (true) {
            view.printMenu();
            String choice = scanner.nextLine();
            choice.trim();

            switch (choice.toLowerCase()) {
                case "\\exit":
                    view.printMessage("Выход из приложения.");
                    scanner.close();
                    System.exit(0);
                case "\\fill":
                    //fillForm();
                    break;
                case "\\all":
                    sequentialFieldFilling();
                    break;
                case "\\users":
                    userMenu();
                    break;
                case "\\save":
                    saveDataToDatabase();
                    break;
                case "\\edit":
                    editField(choice);
                    break;
                case "\\pdf":
                    saveFormToPDF(choice);
                    break;
                case "\\list":
                    //displayFormFields();
                    break;
                default:
                    view.printMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

//    private void fillForm() {
//        fieldController = new FieldController(fieldView, fieldModel, authController);
//        fieldController.handleFieldInput();
//    }

    private void userMenu() {
        userController = new UserController(userView, userModel);
        userController.handleUserInput();
    }

    // Метод для вывода списка всех полей формы
//    private void displayFormFields() {
//        if (formFields.isEmpty()) {
//            view.printMessage("Форма не содержит полей.");
//        } else {
//            view.printMessage("Список всех полей формы:");
//            for (int i = 0; i < formFields.size(); i++) {
//                view.printMessage((i + 1) + ". " + formFields.get(i));
//            }
//        }
//    }

    // Логика для последовательного заполнения полей формы
    private void sequentialFieldFilling() {
        // TODO: Реализовать логику для последовательного заполнения полей формы
        view.printMessage("Последовательное заполнение полей формы (заглушка).");
    }

    // Логика для сохранения данных в БД
    private void saveDataToDatabase() {
        // TODO: Реализовать логику для сохранения данных в БД
        view.printMessage("Сохранение данных в БД (заглушка).");
    }

    // Логика для редактирования определенного поля
    private void editField(String fieldNumber) {
        // TODO: Реализовать логику для редактирования определенного поля
        view.printMessage("Редактирование определенного поля (заглушка). Номер поля: " + fieldNumber);
    }

    // Логика для сохранения формы в PDF файл
    private void saveFormToPDF(String fileName) {
        // TODO: Реализовать логику для сохранения формы в PDF файл
        view.printMessage("Сохранение формы в PDF файл (заглушка). Имя файла: " + fileName);
    }
}
