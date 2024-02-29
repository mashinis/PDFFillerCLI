package ru.mashinis.model.database;

import org.mindrot.jbcrypt.BCrypt;
import ru.mashinis.config.DatabaseConfig;
import ru.mashinis.model.User;
import java.sql.*;

public class AuthModel {
//    private final String dbUrl;
//    private final String dbUsername;
//    private final String dbPassword;
//
//    public AuthModel() {
//        this.dbUrl = DatabaseConfig.getUrl();
//        this.dbUsername = DatabaseConfig.getUsername();
//        this.dbPassword = DatabaseConfig.getPassword();
//    }
//
//    public boolean authenticateUser(String username, String password) {
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
//            String sql = "SELECT * FROM users WHERE user_login = ?";
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                statement.setString(1, username);
//
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    if (resultSet.next()) {
//                        String storedHash = resultSet.getString("password");
//                        // Проверка соответствия введенного пароля и хеша из базы данных
//                        return BCrypt.checkpw(password, storedHash);
//                    }
//                    return false;
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
////    public User registerUser(String username, String userLogin, String password) {
////        // Логин уникален, продолжаем регистрацию
////        if (isLoginUnique(userLogin)) {
////            // Хеширование пароля перед сохранением в базу данных
////            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
////
////            try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
////                String sql = "INSERT INTO users (user_name, user_login, password) VALUES (?, ?, ?)";
////                try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
////                    statement.setString(1, username);
////                    statement.setString(2, userLogin);
////                    statement.setString(3, hashedPassword);
////
////                    int rowsAffected = statement.executeUpdate();
////                    if (rowsAffected > 0) {
////                        // Получение сгенерированного ключа (идентификатора только что добавленной записи)
////                        ResultSet generatedKeys = statement.getGeneratedKeys();
////                        if (generatedKeys.next()) {
////                            int userId = generatedKeys.getInt(1);
////                            // Возвращаем объект User с идентификатором
////                            return getUserById(userId);
////                        }
////                    }
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        } else {
////            // Логин не уникален, выдаем сообщение
////            System.out.println("Логин уже существует. Выберите другой логин.");
////        }
////
////        return null; // В случае ошибки или неудачной регистрации
////    }
//
////    public User getUserByLogin(String login) {
////        String sql = "SELECT * FROM users WHERE user_login = ?";
////        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
////             PreparedStatement statement = connection.prepareStatement(sql)) {
////            statement.setString(1, login);
////
////            try (ResultSet resultSet = statement.executeQuery()) {
////                if (resultSet.next()) {
////                    int userId = resultSet.getInt("id");
////                    String username = resultSet.getString("user_name");
////                    String userLogin = resultSet.getString("user_login");
////                    String password = resultSet.getString("password");
////                    User user = new User(username, userLogin, password);
////                    user.setUserId(userId);
////                    return user;
////                }
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        return null; // В случае ошибки или отсутствия пользователя
////    }
//
////    public User getUserById(int userId) {
////        String sql = "SELECT * FROM users WHERE id = ?";
////        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
////             PreparedStatement statement = connection.prepareStatement(sql)) {
////            statement.setInt(1, userId);
////
////            try (ResultSet resultSet = statement.executeQuery()) {
////                if (resultSet.next()) {
////                    String username = resultSet.getString("user_name");
////                    String userLogin = resultSet.getString("user_login");
////                    String password = resultSet.getString("password");
////                    User user = new User(username, userLogin, password);
////                    user.setUserId(userId);
////                    return user;
////                }
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////        return null; // В случае ошибки или отсутствия пользователя
////    }
//
//    private boolean isLoginUnique(String login) {
//        String sql = "SELECT COUNT(*) FROM users WHERE user_login = ?";
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, login);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int count = resultSet.getInt(1);
//                    return count == 0; // Возвращает true, если логин уникален
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return false; // В случае ошибки
//    }
}
