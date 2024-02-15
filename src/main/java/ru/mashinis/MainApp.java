package ru.mashinis;

import ru.mashinis.controller.FieldController;
import ru.mashinis.model.database.FieldModel;
import ru.mashinis.view.FieldView;

/**
 * PDF-Forms Filler CLI
 * Консольный интерфейс для заполнения PDF форм
 * В систему заложен функционал многопользовательского режима работы,
 * но настоящая версия поддерживает только однопользовательский режим.
 * Программа имеет универсальную возможность работы с формами PDF,
 * благодаря возможности сохранения названия формы и полей для неё в базе данных, включая имя и алиас полей.
 * Функционал сохранения новой формы заложен, но не реализован. Для реализации функционала
 * сохранения новой формы через интерфейс приложения нужно вводить роли пользователя.
 * @version 1.00 15.02.2024
 * @author Гильдебрант Алексей
 */
public class MainApp {
    public static void main(String[] args) {

        FieldView fieldView = new FieldView();
        FieldModel fieldModel = new FieldModel();
        FieldController fieldController = new FieldController(fieldView, fieldModel);

        fieldController.start();
    }
}
