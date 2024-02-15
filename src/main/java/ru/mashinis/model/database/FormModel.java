package ru.mashinis.model.database;

import ru.mashinis.model.Field;
import ru.mashinis.model.Form;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FormModel {
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

    public List<Form> getAllForm() {
        List<Form> forms = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM forms ORDER BY id";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String formName = resultSet.getString("form_name");
                        forms.add(new Form(id, formName));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return forms;
    }

    public int getMaxIdForms() {
        String sql = "SELECT MAX(id) AS max_id FROM forms";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("max_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // В случае ошибки или отсутствия поля
    }
}
