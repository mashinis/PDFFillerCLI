package ru.mashinis.dao.implement;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.dell.Dao;
import ru.mashinis.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * СRUT User
 */
public class UserDaoImpl implements Dao<User> {
    // Добавляем свой пул соединений (HikariDataSource)
    private HikariDataSource dataSource;

    public UserDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        initializeDatabase(); // Вызываем метод инициализации базы данных при создании объекта UserDao
    }

    private void initializeDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            // Здесь вы можете выполнять необходимые операции инициализации базы данных
            // Например, создание таблицы, если ее нет, и добавление начальных данных
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing the database.", e);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

// Этот метод пока нигде не используем.
// Он будет нужен когда у пользователей будут роли и
// Админ сможет просматривать всех пользователей
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                userList.add(mapResultSetToUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (email, password, first_name, patronymic, second_name) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getPatronymic());
            statement.setString(5, user.getSecondName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Пользователю можно изменить только ФИО!
    @Override
    public void update(User user, String[] params) {
        Objects.requireNonNull(user, "User cannot be null");

        if (params != null && params.length >= 3) {
            int userId = user.getId();
            String sql = "UPDATE users SET first_name = ?, patronymic = ?, second_name = ? WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, params[0]);
                statement.setString(2, params[1]);
                statement.setString(3, params[2]);
                statement.setInt(4, userId);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // При удалении нужна будет проверка, чтоб Админ не смог себя удалить
    @Override
    public void delete(User user) {
        int userId = user.getId();
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Дополнительный метод для отображения ResultSet в объект User
    // email, password, first_name, patronymic, second_name
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String firstName = resultSet.getString("first_name");
        String patronymic = resultSet.getString("patronymic");
        String secondName = resultSet.getString("second_name");


        return new User(userId, email, password, firstName, patronymic, secondName);
    }

    public Optional<User> getByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToUser(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
