package ru.mashinis.dao.dell;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.dell.FieldValueDao;
import ru.mashinis.model.FieldValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FieldValueDaoImpl implements FieldValueDao<FieldValue> {
    private HikariDataSource dataSource;

    public FieldValueDaoImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Вернет конкретное поле
    @Override
    public Optional<FieldValue> getById(int id) {
        String sql = "SELECT * FROM field_value WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToForm(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Вернет все поля под идентификатором identifier
    @Override
    public List<FieldValue> getAll(int identifier) {
        List<FieldValue> fieldValues = new ArrayList<>();
        String sql = "SELECT * FROM field_value WHERE user_form_identifier = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, identifier);

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    fieldValues.add(mapResultSetToForm(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fieldValues;
    }

    @Override
    public void save(List<FieldValue> fieldValues) {
        String sql = "INSERT INTO forms (content, user_form_identifier) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (FieldValue fieldValue : fieldValues) {
                statement.setString(1, fieldValue.getContent());
                statement.setInt(2, fieldValue.getUserFormIdentifier());
                statement.addBatch();  // Добавляем запрос в пакет для массовой вставки
            }

            statement.executeBatch();  // Выполняем все запросы в пакете одним вызовом
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(FieldValue fieldValue, String[] params) {

    }

    @Override
    public void delete(FieldValue fieldValue) {

    }

    private FieldValue mapResultSetToForm(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String content = resultSet.getString("content");
        int identifier = resultSet.getInt("user_form_identifier");
        String createdAt = resultSet.getString("created_at");
        return new FieldValue(id, content, identifier, createdAt);
    }
}
