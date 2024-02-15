package ru.mashinis.view;

/**
 * Класс пока не используем. Нужен при будущем расширении системы
 */
public class UserView {
    public void printMenu() {
        System.out.println("Меню пользователей.\nВыберите действие:");
        System.out.println("\\menu - Выйти в главное меню");
        System.out.println("\\reg - Зарегистрировать нового пользователя");
        System.out.println("\\all - Показать всех пользователей");
        System.out.println("\\user (id) - Показать пользователя с указанным id");
        System.out.println("\\exit. Выйти из приложения");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
