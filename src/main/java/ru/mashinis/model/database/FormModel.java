package ru.mashinis.model.database;

import ru.mashinis.config.DatabaseConfig;
import ru.mashinis.model.Field;
import ru.mashinis.model.Form;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormModel {
    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    public FormModel() {
        this.dbUrl = DatabaseConfig.getUrl();
        this.dbUsername = DatabaseConfig.getUsername();
        this.dbPassword = DatabaseConfig.getPassword();
    }

//    public List<Form> getAllForm() {
//        List<Form> forms = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
//            String sql = "SELECT * FROM forms ORDER BY id";
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                try (ResultSet resultSet = statement.executeQuery()) {
//                    while (resultSet.next()) {
//                        int id = resultSet.getInt("id");
//                        String formName = resultSet.getString("form_name");
//                        forms.add(new Form(id, formName));
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return forms;
//    }

    public int getMaxIdForms() {
        String sql = "SELECT MAX(id) AS max_id FROM forms";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
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

    public String getNameIdForm(int formId) {
        String sql = "SELECT * FROM forms WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, formId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("form_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // В случае ошибки или отсутствия поля
    }
}
