package ru.mashinis.model.database;

import ru.mashinis.config.DatabaseConfig;
import ru.mashinis.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    public UserModel() {
        this.dbUrl = DatabaseConfig.getUrl();
        this.dbUsername = DatabaseConfig.getUsername();
        this.dbPassword = DatabaseConfig.getPassword();
    }

    public void saveUser(User user) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String sql = "INSERT INTO users (user_name, user_login, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getLogin());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM users";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = Integer.parseInt(resultSet.getString("id"));
                        String username = resultSet.getString("user_name");
                        String userLogin = resultSet.getString("user_login");
                        String password = resultSet.getString("password");
                        User user = new User(username, userLogin, password);
                        user.setUserId(id);
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String username = resultSet.getString("user_name");
                    String userLogin = resultSet.getString("user_login");
                    String password = resultSet.getString("password");
                    User user = new User(username, userLogin, password);
                    user.setUserId(userId);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // В случае ошибки или отсутствия пользователя
    }

    public boolean validateUserCredentials(String login, String password) {
        String sql = "SELECT * FROM users WHERE user_login = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Вернет true, если пользователь существует и пароль верен
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // В случае ошибки или отсутствия пользователя
    }
}
