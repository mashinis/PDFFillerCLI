package ru.mashinis;

import ru.mashinis.controller.InputController;
import ru.mashinis.model.InputModel;
import ru.mashinis.view.InputView;

/**
 * Класс Main для запуска приложения
 */
public class Main {
    public static void main(String[] args) {
        InputModel model = new InputModel();
        InputView view = new InputView();
        InputController controller = new InputController(model, view);

        // Запуск приложения
        controller.start();
    }
}
