package ru.mashinis.controller;

import ru.mashinis.model.Field;
import ru.mashinis.model.FieldValue;
import ru.mashinis.model.User;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.model.database.FieldValueModel;
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
    private int fieldId;
    private int userId;
    private int formId;
    private int userInstanceMaxId;
    private AuthController authController;
    private  int instanceId;

    public FieldValueController(FieldValueView fieldValueView, FieldValueModel fieldValueModel, AuthController authController) {
        this.fieldValueView = fieldValueView;
        this.fieldValueModel = fieldValueModel;
        this.scanner = new Scanner(System.in);
        this.userId = authController.getAuthenticatedUser().getUserId();
        this.userInstanceMaxId = fieldValueModel.getUserMaxInstance(userId);
        fieldValueList = new ArrayList<>();
    }


    public void handleFieldInput() {
        fieldValueList = fieldValueModel.getFormInstanceId(userId, userInstanceMaxId);
        int count = 1;
        if (fieldValueList == null) {
            fieldValueView.printMessage("Запись отсутствует!");
        } else {
            for (FieldValue fieldValue : fieldValueList) {
                fieldValueView.printMessage(count + ". " + fieldValue.getFieldName());
                //

            }
        }
    }

    public void sequentFillForm() {
        fieldValueView.printMessage("Выберите, какую форму будете заполнять.");
        String input = scanner.nextLine();

        while (true) {
            try {
                formId = Integer.parseInt(input);
                break;
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
            //System.out.println(input + ", " + id + ", " + userId + ", " + formId + ", " + instance);
            fieldValueList.add(new FieldValue(input, id, userId, formId, instance));
            count++;
        }

        fieldValueModel.addFieldValues(fieldValueList);
    }
}