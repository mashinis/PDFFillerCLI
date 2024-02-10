package ru.mashinis.view;

/**
 * Представление, отвечающее за вывод информации и приглашения к вводу
 */
public class InputView {

    public void printMenu() {
        System.out.println();
        System.out.println("Главное меню.\nВыберете действие:");
        System.out.println("\\fill - Меню заполнения формы");
        System.out.println("\\all - Последовательное заполнение всех полей формы;");
        System.out.println("\\users - Меню пользователей;");
        System.out.println("\\save - Сохранить данные в БД;");
        System.out.println("\\edit (номер поля) - Редактировать определенное поле;");
        System.out.println("\\pdf (имя файла) - Сохранить форму в PDF файл;");
        System.out.println("\\list - Список всех полей формы.");
        System.out.println("\\exit - Выход из приложения;");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}
