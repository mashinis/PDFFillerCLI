package ru.mashinis.dao;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfacedao.Dao;
import ru.mashinis.model.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FieldDaoImpl implements Dao<Field> {
    private HikariDataSource dataSource;

    public FieldDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
        initializeDatabase();
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
    public Optional<Field> getById(int id) {
        String sql = "SELECT * FROM fields WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToField(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Field> getAll() {
        List<Field> fieldList = new ArrayList<>();
        String sql = "SELECT * FROM fields";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                fieldList.add(mapResultSetToField(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fieldList;
    }

    // Метод будет использоваться, когда у пользователей будут роли.
    // Только Модератор и Админ смогут добавлять новые поля в форму в БД
    @Override
    public void save(Field field) {
        String sql = "INSERT INTO fields (name, alias) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, field.getName());
            statement.setString(2, field.getAlias());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод будет использоваться, когда у пользователей будут роли.
    // Только владелец сможет редактировать название своих форм
    @Override
    public void update(Field field, String[] params) {
        Objects.requireNonNull(field, "Form cannot be null");

        if (params != null && params.length >= 1) {
            int fieldId = field.getId();
            String sql = "UPDATE fields SET name = ? WHERE id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, params[0]);
                statement.setInt(2, fieldId);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод будет использоваться, когда у пользователей будут роли.
    // Только владелец сможет удалить свою форму
    @Override
    public void delete(Field field) {
        int fieldId = field.getId();
        String sql = "DELETE FROM fields WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, fieldId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Дополнительный метод для отображения ResultSet в объект Form
    private Field mapResultSetToField(ResultSet resultSet) throws SQLException {
        int fieldId = resultSet.getInt("id");
        String fieldName = resultSet.getString("name");
        String fieldAlias = resultSet.getString("alias");
        String createdAt = resultSet.getString("created_at");
        return new Field(fieldId, fieldName, fieldAlias, createdAt);
    }
}
