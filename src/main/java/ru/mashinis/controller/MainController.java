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
    private int maxIdForm;
    private int userPdfIdForm;

    public FieldController(FieldView fieldView, FieldModel fieldModel) {
        this.fieldView = fieldView;
        this.fieldModel = fieldModel;
        this.fieldValueView = new FieldValueView();
        this.fieldValueModel = new FieldValueModel();
        this.authModel = new AuthModel();
        this.authView = new AuthView();
        this.maxIdForm = new FormModel().getMaxIdForms();
        this.userPdfIdForm = 0;
    }

    // Метод для авторизации
    public void start() {
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
                case "\\fields": // Вывод всех полей выбранной формы
                    try {
                        int i = stringToInt(s[1].trim());
                        if (i != -1) {
                            listFields(i);
                            break;
                        } else {
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Не верный формат! Повторите ввод.");
                        continue;
                    }
                case "\\all": // Последовательное заполнение полей
                    try {
                        int i = stringToInt(s[1].trim());
                        if (i != -1) {
                            fillAllField(i);
                            break;
                        } else {
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Не верный формат! Повторите ввод.");
                        continue;
                    }
                case "\\pdf": // Заполнить форму PDF данными из БД
                    try {
                        int i = stringToInt(s[1].trim());
                        if (i != -1) {
                            fillPdfForm(i);
                            break;
                        } else {
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("Не верный формат! Повторите ввод.");
                        continue;
                    }
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

    private void fillAllField(int idForm) {
        try {
            if (idForm < 1 || idForm > maxIdForm) {
                fieldView.displayErrorMessage("Такой формы не существует! Повторите ввод.");
                return;
            }

            fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
            userPdfIdForm = fieldValueController.sequentFillForm(idForm);

            fieldView.printMessage("Хотите заполнить PDF из введенных данных? (Да/Нет)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.toLowerCase().startsWith("да")) {
                fillPdfForm(userPdfIdForm);
            } else {
                fieldView.printMessage("Не известная команда.");
            }

        } catch (NumberFormatException e) {
            fieldView.displayErrorMessage("Ошибка: Введено не число. Попробуйте еще раз.");
        }


    }

    /**
     * Заполняю PDF форму из данных в БД, хранящихся под идентификатором: PDF ID Form
     */
    private void fillPdfForm(int pdfIdForm) {
        boolean isPdfIdForm = fieldValueModel.isUserInstance(pdfIdForm);

        if (isPdfIdForm) {
            fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
            fieldValueController.fillPdfForm(pdfIdForm);
        } else {
            fieldView.displayErrorMessage("Такого идентификатора не существует! Повторите ввод.");
        }
    }

    private void listFields(int idForm) {

        if (idForm < 1 || idForm > maxIdForm) {
            fieldView.displayErrorMessage("Такой формы не существует! Повторите ввод.");
            return;
        }

        List<Field> fields = fieldModel.getAllFields(idForm);
        int count = 1;
        String nameForm = new FormModel().getNameIdForm(idForm);
        fieldView.printMessage("\n" + nameForm + "\nСписок доступных полей:");
        for (Field field : fields) {
            fieldView.printMessage(count++ + ". " + field.getName());
        }

        fieldView.printMessage("");
    }

    private int stringToInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return i;

        } catch (NumberFormatException e) {
            fieldView.displayErrorMessage("Ошибка: Введено не число. Попробуйте еще раз.");
            return -1;
        }
    }
}
