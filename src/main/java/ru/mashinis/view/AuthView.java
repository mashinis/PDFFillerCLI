package ru.mashinis.view;

public class AuthView {
    public void displayMenu() {
        System.out.println("Для работы с системой требуется аутентификации.\nВыберите действие:");
        System.out.println("\\login - Войти в систему");
        System.out.println("\\register - Зарегистрировать нового пользователя");
        System.out.println("\\exit - Выйти из приложения");
    }

    public void promptUsername() {
        System.out.print("Введите имя: ");
    }

    public void promptUserLogin() {
        System.out.print("Введите логин: ");
    }

    public void promptPassword() {
        System.out.print("Введите пароль: ");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Ошибка: " + message);
    }

    public void displaySuccessMessage(String message) {
        System.out.println("Успех: " + message);
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}
