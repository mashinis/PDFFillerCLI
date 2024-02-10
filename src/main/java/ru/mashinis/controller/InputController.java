package ru.mashinis.controller;

import ru.mashinis.model.InputModel;
import ru.mashinis.view.InputView;

import java.util.Scanner;

/**
 * Контроллер, управляющий взаимодействием между InputModel и InputView
 */
public class InputController {
    private InputModel model;
    private InputView view;
    private Scanner scanner;

    public InputController(InputModel model, InputView view) {
        this.model = model;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    // Метод обработки ввода пользователя в бесконечном цикле
    public void processUserInput() {
        String userInput;

        do {
            view.printMessage("Ожидается ввод строки от пользователя. Введите '\\exit' для выхода.");

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
            // Логика для последовательного заполнения полей формы
            view.printMessage("Последовательное заполнение полей формы.");
        } else if (userInput.equalsIgnoreCase("\\save")) {
            // Логика для сохранения данных в БД
            view.printMessage("Сохранение данных в БД.");
        } else if (userInput.toLowerCase().startsWith("\\edit")) {
            // Логика для редактирования определенного поля
            view.printMessage("Редактирование определенного поля.");
        } else if (userInput.toLowerCase().startsWith("\\pdf")) {
            // Логика для сохранения формы в PDF файл
            view.printMessage("Сохранение формы в PDF файл.");
        } else if (userInput.equalsIgnoreCase("\\list")) {
            // Логика для вывода списка всех полей формы
            view.printMessage("Список всех полей формы.");
        } else {
            // Обработка других вариантов ввода
            view.printMessage("Неверная команда. Попробуйте еще раз.");
        }
    }
}
