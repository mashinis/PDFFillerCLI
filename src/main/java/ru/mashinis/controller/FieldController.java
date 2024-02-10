package ru.mashinis.controller;

import ru.mashinis.model.Field;
import ru.mashinis.model.User;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.view.FieldView;

import java.util.List;
import java.util.Scanner;

public class FieldController {
    private final FieldView view;
    private final FieldModel fieldModel;

    public FieldController(FieldView view, FieldModel fieldModel) {
        this.view = view;
        this.fieldModel = fieldModel;
    }

    public void handleFieldInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            view.printMenu();
            String input = scanner.nextLine();
            String[] s = input.toLowerCase().split(" ");
            String choice = s[0].trim();

            switch (choice.toLowerCase()) {
                case "\\menu":
                    return;  // Возврат в главное меню
                case "\\list": // Список всех полей
                    listField();
                    break;
                case "\\all": // Последовательное заполнение полей
                    //fillAllField();
                    break;
                case "\\edit": // Заполнить поле по его id
                    //fillByIdField(s[1].trim());
                    break;
                case "\\exit":
                    scanner.close();
                    System.exit(0);
                default:
                    view.printMessage("Неверная команда. Попробуйте еще раз.");
            }
        }
    }

    private void listField() {
        List<Field> fields = fieldModel.getAllFields();
        if (fields.isEmpty()) {
            view.printMessage("Нет полей форм.");
        } else {
            view.printMessage("Список полей:");
            for (Field field : fields) {
                view.printMessage(field.toString());
            }
        }
    }
}
