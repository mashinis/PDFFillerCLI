package ru.mashinis.view;

public class FieldView {
    public void printMenu() {
        System.out.println("Меню заполнения формы.\nВыберите действие:");
        System.out.println("\\menu - Выйти в главное меню");
        System.out.println("\\list - Список всех полей формы.");
        System.out.println("\\all - Последовательное заполнение всех полей формы;");
        System.out.println("\\edit (номер поля) - Редактировать определенное поле;");
        System.out.println("\\exit - Выход из приложения;");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
