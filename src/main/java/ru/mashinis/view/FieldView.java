package ru.mashinis.view;

public class FieldView {
    public void printMenu() {
        System.out.println("Меню заполнения формы.\nВыберите действие:");
        System.out.println("\\forms - Список всех доступных форм;");
        System.out.println("\\forms [idForm] - Список всех всех полей выбранной формы;");
        System.out.println("\\all - Последовательное заполнение всех полей формы;");
        System.out.println("\\pdf - Заполнить PDF из последних данных пользователя.");
        //System.out.println("\\edit (номер поля) - Редактировать определенное поле;");
        System.out.println("\\exit - Выход из приложения;");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
