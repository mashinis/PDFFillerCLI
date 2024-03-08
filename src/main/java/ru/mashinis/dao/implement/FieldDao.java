package ru.mashinis.dao.implement;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.Read;
import ru.mashinis.model.Field;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FieldDao implements Read<Field>{
    private HikariDataSource dataSource;

    public FieldDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Field> getById(int id) {
        String sql = "SELECT * FROM fields WHERE field_id = ?";
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

    public List<Field> getAll(int formId) {
        List<Field> fieldList = new ArrayList<>();
        String sql = "SELECT * FROM fields WHERE form_id = ? ORDER BY field_name";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, formId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    fieldList.add(mapResultSetToField(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fieldList;
    }

    private Field mapResultSetToField(ResultSet resultSet) throws SQLException {
        int field_id = resultSet.getInt("field_id");
        int formId = resultSet.getInt("form_id");
        String fieldName = resultSet.getString("field_name");
        String fieldAlias = resultSet.getString("field_alias");
        String createdAt = resultSet.getString("created_at");

        return new Field(field_id, formId, fieldName, fieldAlias, createdAt);
    }

    public void create(List<Field> fields, int formId) {
        String sql = "INSERT INTO fields (form_id, field_name, field_alias) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Field field : fields) {
                statement.setInt(1, formId);
                statement.setString(2, field.getFieldName());
                statement.setString(3, field.getFieldAlias());
                statement.addBatch();  // Добавляем оператор INSERT в батч
            }

            // Выполняем все операторы в батче одним запросом
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
