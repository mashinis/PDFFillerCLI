package ru.mashinis.model.database;

import org.mindrot.jbcrypt.BCrypt;
import ru.mashinis.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class AuthModel {
    private static final String PROPERTIES_FILE = "properties/application.properties";
    private static String url;
    private static String dbUsername;
    private static String dbPassword;

    static {
        loadDatabaseProperties();
    }

    private static void loadDatabaseProperties() {
        Properties properties = new Properties();
        try (InputStream input = AuthModel.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
            url = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "SELECT * FROM users WHERE user_login = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String storedHash = resultSet.getString("password");
                        // Проверка соответствия введенного пароля и хеша из базы данных
                        return BCrypt.checkpw(password, storedHash);
                    }
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User registerUser(String username, String userLogin, String password) {
        // Хеширование пароля перед сохранением в базу данных
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
            String sql = "INSERT INTO users (user_name, user_login, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, username);
                statement.setString(2, userLogin);
                statement.setString(3, hashedPassword);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    // Получение сгенерированного ключа (идентификатора только что добавленной записи)
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        // Возвращаем объект User с идентификатором
                        return getUserById(userId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // В случае ошибки или неудачной регистрации
    }

    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE user_login = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
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

    public static User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
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
}
