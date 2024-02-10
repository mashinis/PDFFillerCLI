package ru.mashinis.controller;

import ru.mashinis.model.FormField;
import ru.mashinis.model.InputModel;
import ru.mashinis.view.InputView;

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

    public InputController(InputModel model, InputView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.formFields = new ArrayList<>();
    }

    // Метод обработки ввода пользователя в бесконечном цикле
    public void processUserInput() {
        String userInput;

        do {
            view.printMessage(
                            "Заполните форму PDF.\n" +
                            "\\0 - Последовательное заполнение полей формы.\n" +
                            "\\exit - Выход из приложения;\n" +
                            "\\save - Сохранить данные в БД;\n" +
                            "\\edit (номер поля) - Редактировать определенное поле;\n" +
                            "\\pdf (имя файла) - Сохранить форму в PDF файл;\n" +
                            "\\list - Список всех полей формы."
            );

            // Чтение ввода пользователя
            userInput = scanner.nextLine();
            model.setUserInput(userInput);

            // Обработка команд
            processCommands();

        } while (!userInput.equalsIgnoreCase("\\exit"));

        // Закрытие сканнера
        scanner.close();
    }

    // Метод для обработки команд
    private void processCommands() {
        String userInput = model.getUserInput();

        if (userInput.equalsIgnoreCase("\\exit")) {
            view.printMessage("Выход из приложения.");
            return;
        }

        if (userInput.equalsIgnoreCase("\\0")) {
            sequentialFieldFilling();
        } else if (userInput.equalsIgnoreCase("\\save")) {
            saveDataToDatabase();
        } else if (userInput.toLowerCase().startsWith("\\edit")) {
            editField(userInput);
        } else if (userInput.toLowerCase().startsWith("\\pdf")) {
            saveFormToPDF(userInput);
        } else if (userInput.equalsIgnoreCase("\\list")) {
            displayFormFields();
        } else {
            view.printMessage("Неверная команда. Попробуйте еще раз.");
        }
    }

    // Новый метод для вывода списка всех полей формы
    private void displayFormFields() {
        if (formFields.isEmpty()) {
            view.printMessage("Форма не содержит полей.");
        } else {
            view.printMessage("Список всех полей формы:");
            for (int i = 0; i < formFields.size(); i++) {
                view.printMessage((i + 1) + ". " + formFields.get(i));
            }
        }
    }

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
