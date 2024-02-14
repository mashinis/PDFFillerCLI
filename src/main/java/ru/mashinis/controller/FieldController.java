package ru.mashinis.controller;

import ru.mashinis.model.Field;
import ru.mashinis.model.User;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.model.database.FieldValueModel;
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
    private AuthController authController;

    public FieldController(FieldView fieldView, FieldModel fieldModel, AuthController authController) {
        this.fieldView = fieldView;
        this.fieldModel = fieldModel;
        this.fieldValueView = new FieldValueView();
        this.fieldValueModel = new FieldValueModel();
        this.authController = authController;
    }

    public void handleFieldInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            fieldView.printMenu();
            String input = scanner.nextLine();
            String[] s = input.toLowerCase().split(" ");
            String choice = s[0].trim();

            switch (choice.toLowerCase()) {
                case "\\menu":
                    return;  // Возврат в главное меню
                case "\\pdf": // Список всех полей
                    fillPdfForm();
                    break;
                case "\\all": // Последовательное заполнение полей
                    fillAllField();
                    break;
                case "\\edit": // Заполнить поле по его id
                    //fillByIdField(s[1].trim());
                    break;
                case "\\exit":
                    scanner.close();
                    System.exit(0);
                default:
                    fieldView.printMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void fillAllField() {
        fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
        fieldValueController.sequentFillForm();
    }

    private void fillPdfForm() {
        fieldValueController = new FieldValueController(fieldValueView, fieldValueModel, authController);
        fieldValueController.fillPdfForm();
    }

    private List<Field> listField() {
        List<Field> list = fieldModel.getAllFields();
        return list; // Пока не обрабатываю условие, что может быть несколько форм
    }
}
