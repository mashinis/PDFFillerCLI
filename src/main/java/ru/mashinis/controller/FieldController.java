package ru.mashinis.controller;

import ru.mashinis.model.Field;
import ru.mashinis.model.Form;
import ru.mashinis.model.database.AuthModel;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.model.database.FieldValueModel;
import ru.mashinis.model.database.FormModel;
import ru.mashinis.view.AuthView;
import ru.mashinis.view.FieldValueView;
import ru.mashinis.view.FieldView;

import java.util.List;
import java.util.Scanner;

public class FieldController {
    private final FieldView fieldView;
    private final FieldModel fieldModel;
    private FieldValueView fieldValueView;
    private FieldValueModel fieldValueModel;
    private FieldValueController fieldValueController;
    private AuthModel authModel;
    private AuthView authView;
    private AuthController authController;

    public FieldController(FieldView fieldView, FieldModel fieldModel) {
        this.fieldView = fieldView;
        this.fieldModel = fieldModel;
        this.fieldValueView = new FieldValueView();
        this.fieldValueModel = new FieldValueModel();
        this.authModel = new AuthModel();
        this.authView = new AuthView();
    }

    // Метод для авторизации
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

    // Главное меню приложения
    public void handleMainMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            fieldView.printMenu();
            String input = scanner.nextLine();
            String[] s = input.toLowerCase().split(" ");
            String choice = s[0].trim();

            switch (choice.toLowerCase()) {
                case "\\forms": // Список всех доступных форм
                    formsList();
                    break;
                case "\\fills": // Вывод всех полей выбранной формы
                    try {
                        listFields(s[1].trim());
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Не верный формат! Повторите ввод.");
                        continue;
                    }
                case "\\all": // Последовательное заполнение полей
                    fillAllField();
                    break;
//                case "\\edit": // Заполнить поле по его id
//                    fillByIdField(s[1].trim());
//                    break;
                case "\\pdf": // Заполнить форму PDF данными из БД
                    fillPdfForm();
                    break;
                case "\\exit":
                    scanner.close();
                    System.exit(0);
                default:
                    fieldView.printMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void formsList() {
        List<Form> forms = new FormModel().getAllForm();
        fieldView.printMessage("Список доступных форм:");
        for (Form form : forms) {
            fieldView.printMessage(form.toString() + "\n");
        }
    }

    private void fillAllField() {
        int maxIdForms = new FormModel().getMaxIdForms();
        fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
        fieldValueController.sequentFillForm(maxIdForms);
    }

    /**
     * В БД хранится числовой идентификатор последней заполненной формы
     */
    private void fillPdfForm() {
        fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
        fieldValueController.fillPdfForm();
    }

    private void listFields(String s) {
        try {
            int i = (Integer.parseInt(s));
            if (i < 1 || i > new FormModel().getMaxIdForms()) {
               fieldView.displayErrorMessage("Такой формы не существует! Повторите ввод.");
            }
            List<Field> fields = fieldModel.getAllFields(i);
            int count = 1;
            for (Field field : fields) {
                fieldView.printMessage(count++ + ". " + field.getName());
            }
        } catch (NumberFormatException e) {
            fieldView.displayErrorMessage("Ошибка: Введено не число. Попробуйте еще раз.");
        }
        fieldView.printMessage("");
    }
}
