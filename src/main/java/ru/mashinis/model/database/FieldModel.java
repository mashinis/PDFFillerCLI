package ru.mashinis.model.database;

import ru.mashinis.model.Field;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FieldModel {
    private static final String PROPERTIES_FILE = "properties/application.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        loadDatabaseProperties();
    }

    private static void loadDatabaseProperties() {
        Properties properties = new Properties();
        try (InputStream input = UserModel.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Field> getAllFields(int idForm) {
        List<Field> fields = new ArrayList<>();
        String sql = "SELECT * FROM fields WHERE form_id = ? ORDER BY id";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idForm);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = Integer.parseInt(resultSet.getString("id"));
                        String fieldName = resultSet.getString("field_name");
                        String fieldAlias = resultSet.getString("field_alias");
                        int formId = resultSet.getInt("form_id");
                        Field field = new Field(fieldName, fieldAlias, formId);
                        field.setId(id);
                        fields.add(field);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }

    public Field getFieldById(int fieldId) {
        String sql = "SELECT * FROM fields WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, fieldId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = Integer.parseInt(resultSet.getString("id"));
                    String fieldName = resultSet.getString("field_name");
                    String fieldAlias = resultSet.getString("field_alias");
                    int formId = resultSet.getInt("form_id");
                    Field field = new Field(fieldName, fieldAlias, formId);
                    field.setId(id);
                    return field;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // В случае ошибки или отсутствия поля
    }

    public List<Field> getFieldsByFormId(int formId) {
        List<Field> fields = new ArrayList<>();
        String sql = "SELECT * FROM fields WHERE form_id = ? ORDER BY id";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, formId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Field field = new Field();  // Создаем новый объект Field для каждой строки
                    int id = resultSet.getInt("id");
                    String fieldName = resultSet.getString("field_name");
                    field.setId(id);
                    field.setName(fieldName);
                    fields.add(field);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fields;
    }
}
