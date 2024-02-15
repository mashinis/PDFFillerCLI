package ru.mashinis.controller;

import ru.mashinis.model.Field;
import ru.mashinis.model.FieldValue;
import ru.mashinis.model.User;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.model.database.FieldValueModel;
import ru.mashinis.pdfgeneration.CyrillicPdfFormFiller;
import ru.mashinis.view.FieldValueView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FieldValueController {
    private final FieldValueView fieldValueView;
    private final FieldValueModel fieldValueModel;
    private List<FieldValue> fieldValueList;
    private List<Field> fieldList;
    private Scanner scanner;
    private int userId;
    private int formId;
    private int userInstanceMaxId;

    public FieldValueController(FieldValueView fieldValueView, FieldValueModel fieldValueModel, AuthController authController) {
        this.fieldValueView = fieldValueView;
        this.fieldValueModel = fieldValueModel;
        this.scanner = new Scanner(System.in);
        this.userId = authController.getAuthenticatedUser().getUserId();
        this.userInstanceMaxId = fieldValueModel.getUserMaxInstance(userId);
        fieldValueList = new ArrayList<>();
    }


    public void fillPdfForm() {
        fieldValueList = fieldValueModel.getFormInstanceId(userId, userInstanceMaxId);
        if (fieldValueList == null) {
            fieldValueView.printMessage("Запись отсутствует!");
        } else {
            CyrillicPdfFormFiller cyrillicPdfFormFiller = new CyrillicPdfFormFiller();
            cyrillicPdfFormFiller.fillPdfForm(fieldValueList);
        }
    }

    public void sequentFillForm(int maxIdForms) {
        fieldValueView.printMessage("Выберите, какую форму будете заполнять.");
        String input = null;

        while (true) {
            try {
               input = scanner.nextLine();
                formId = Integer.parseInt(input);

                if (formId < 1 || formId > maxIdForms) {
                    fieldValueView.printMessage("Такой формы не существует! Повторите ввод.");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Введено не число. Попробуйте еще раз.");
            }
        }

        FieldModel fieldModel = new FieldModel();
        fieldList = fieldModel.getFieldsByFormId(formId);
        int count = 1;
        int instance = userInstanceMaxId + 1;
        for (Field field : fieldList) {
            int id = field.getId();
            String name = field.getName();
            fieldValueView.printMessage(count + ". " + name);
            input = scanner.nextLine();
            fieldValueList.add(new FieldValue(input, id, userId, formId, instance));
            count++;
        }

        fieldValueModel.addFieldValues(fieldValueList);

        scanner.close();
    }
}