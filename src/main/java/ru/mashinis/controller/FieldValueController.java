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
//    private final FieldValueView fieldValueView;
//    private final FieldValueModel fieldValueModel;
    private List<FieldValue> fieldValueList;
    private List<Field> fieldList;
    private Scanner scanner;
    private int userId;
    private int userInstanceMaxId;

    public FieldValueController(FieldValueView fieldValueView, FieldValueModel fieldValueModel, AuthController authController) {
//        this.fieldValueView = fieldValueView;
//        this.fieldValueModel = fieldValueModel;
//        this.scanner = new Scanner(System.in);
//        //this.userId = authController.getAuthenticatedUser().getUserId();
//        this.userInstanceMaxId = fieldValueModel.getUserMaxInstance(userId);
//        fieldValueList = new ArrayList<>();
    }


//    public void fillPdfForm(int pdfIdForm) {
//        fieldValueList = fieldValueModel.getFormInstanceId(userId, pdfIdForm);
//        if (fieldValueList == null) {
//            fieldValueView.printErrorMessage("Запись отсутствует!");
//        } else {
//            CyrillicPdfFormFiller cyrillicPdfFormFiller = new CyrillicPdfFormFiller();
//            //cyrillicPdfFormFiller.fillPdfForm(fieldValueList);
//        }
//    }

//    public int sequentFillForm(int idForm) {
//
//        FieldModel fieldModel = new FieldModel();
//        fieldList = fieldModel.getFieldsByFormId(idForm);
//        int count = 1;
//        int pdfIdForm = userInstanceMaxId + 1;
//
//        for (Field field : fieldList) {
//            int fieldId = field.getId();
//            String name = field.getName();
//            fieldValueView.printMessage(count + ". " + name);
//            String value = scanner.nextLine();
//            fieldValueList.add(new FieldValue(value, fieldId, userId, idForm, pdfIdForm));
//            count++;
//        }
//
//        fieldValueModel.addFieldValues(fieldValueList);
//        fieldValueView.printMessage("Ваш PDF ID Form: " + pdfIdForm + "\nЗапомните его. Он потребуется для сохранения в PDF");
//
//        return pdfIdForm;
//    }
}