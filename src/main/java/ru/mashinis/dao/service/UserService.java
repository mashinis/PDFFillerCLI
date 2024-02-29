package ru.mashinis.service;

import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import ru.mashinis.dao.implement.UserDao;
import ru.mashinis.model.User;

/**
 * Класс предназначен для валидации email,
 * а также, для хеширования пароля при регистрации
 * и валидации хеша пароля при авторизации.
 */
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean isEmailUnique(String email) {
        return userDao.getByEmail(email).isEmpty();
    }

    public boolean isPasswordSecure(String password) {
        // Реализация проверки сложности пароля
        // Потом нужно реализовать алгоритм сложности пароля -
        // состоящий из 8 символов, хотя бы одна цифра,
        // одна буква в нижнем регистре,
        // хотя бы одна буква в верхнем регистре
        return password.length() >= 4;  // На примере минимальной длины пароля
    }

    public boolean registerUser(User user) {
        String password = user.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDao.save(user);
        return true;
    }

    public User authenticateUser(String email, String enteredPassword) {
        Optional<User> userOptional = userDao.getByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String storedPassword = user.getPassword();

            // Реализация проверки сопоставления паролей
            if (isPasswordMatch(enteredPassword, storedPassword)) {
                return user;
            }
        }
        return null; // Если пользователя с указанным email нет
    }

    private boolean isPasswordMatch(String enteredPassword, String storedPassword) {

        return BCrypt.checkpw(enteredPassword, storedPassword);
    }
}
