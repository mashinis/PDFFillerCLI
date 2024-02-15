package ru.mashinis.view;

public class FieldView {
    public void printMenu() {
        System.out.println("Меню заполнения формы.\nВыберите действие:");
        System.out.println("\\forms - Список всех доступных форм;");
        System.out.println("\\fields [id Form] - Список всех доступных полей выбранной формы;");
        System.out.println("\\all [id Form] - Последовательное заполнение всех полей формы;");
        System.out.println("\\pdf [PDF ID Form] - Заполнить PDF форму;");
        System.out.println("\\exit - Выход из приложения.");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void displayErrorMessage(String message) {
        System.err.println("Ошибка: " + message);
    }
}
