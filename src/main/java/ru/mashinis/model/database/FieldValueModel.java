package ru.mashinis.model.database;

import ru.mashinis.config.DatabaseConfig;
import ru.mashinis.model.FieldValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FieldValueModel {
//    private final String dbUrl;
//    private final String dbUsername;
//    private final String dbPassword;
//
//    public FieldValueModel() {
//        this.dbUrl = DatabaseConfig.getUrl();
//        this.dbUsername = DatabaseConfig.getUsername();
//        this.dbPassword = DatabaseConfig.getPassword();
//    }
//
//    // Вывод всех полей заполненной формы instanceId, пользователя userId,
//    public List<FieldValue> getFormInstanceId(int userId, int instanceId) {
//        List<FieldValue> list = new ArrayList<>();
//        String sql = "SELECT field_values.id, field_values.value, field_values.form_instance_id, " +
//                "fields.field_name, fields.field_alias, forms.form_name, users.user_name " +
//                "FROM field_values " +
//                "INNER JOIN fields ON field_values.field_id = fields.id " +
//                "INNER JOIN forms ON field_values.form_id = forms.id " +
//                "INNER JOIN users ON field_values.user_id = users.id " +
//                "WHERE field_values.user_id = ? AND field_values.form_instance_id = ? " +
//                "ORDER BY field_values.id;";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, userId);
//            statement.setInt(2, instanceId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    int id = resultSet.getInt("id");
//                    String value = resultSet.getString("value");
//                    int instanceId2 = resultSet.getInt("form_instance_id");
//                    String fieldName = resultSet.getString("field_name");
//                    String fieldAlias = resultSet.getString("field_alias");
//                    String formName = resultSet.getString("form_name");
//                    String userName = resultSet.getString("user_name");
//
//                    FieldValue fieldValue = new FieldValue(id, value, instanceId2, fieldName, formName, userName);
//                    fieldValue.setFieldAlias(fieldAlias);
//
//                    list.add(fieldValue);
//                }
//                return list;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null; // В случае ошибки или отсутствия поля
//    }
//
//    // Получаю максимальный элемент из поля form_instance_id для конкретного пользователя
//    public int getUserMaxInstance(int userId) {
//        String sql = "SELECT MAX(form_instance_id) AS max_instance_id FROM field_values WHERE user_id = ?";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, userId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt("max_instance_id");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return 0; // В случае ошибки или отсутствия поля
//    }
//
//    // Добавляем одну запись в таблицу
//    public void addOneFieldValue(String value, int fieldId, int userId, int formId, int formInstanceId) {
//        String sql = "INSERT INTO field_values (value, field_id, user_id, form_id, form_instance_id) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, value);
//            statement.setInt(2, fieldId);
//            statement.setInt(3, userId);
//            statement.setInt(4, formId);
//            statement.setInt(5, formInstanceId);
//
//            statement.executeUpdate();
//            System.out.println("Запись успешно добавлена в таблицу field_values.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Ошибка при добавлении записи в таблицу field_values: " + e.getMessage());
//        }
//    }
//
//    // Показываем этот метод во время защиты проекта
//    // Добавляем все записи. В случае ошибки, не добавляем ни одной!
//    public void addFieldValues(List<FieldValue> fieldValuesList) {
//        String sql = "INSERT INTO field_values (value, field_id, user_id, form_id, form_instance_id) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
//            connection.setAutoCommit(false);  // Отключаем автоматический коммит
//
//            try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                for (FieldValue fieldValue : fieldValuesList) {
//                    statement.setString(1, fieldValue.getValue());
//                    statement.setInt(2, fieldValue.getFieldId());
//                    statement.setInt(3, fieldValue.getUserId());
//                    statement.setInt(4, fieldValue.getFormId());
//                    statement.setInt(5, fieldValue.getFormInstanceId());
//
//                    statement.addBatch();  // Добавляем запрос в пакет
//                }
//
//                int[] result = statement.executeBatch();  // Выполняем все запросы в пакете
//
//                for (int i : result) {
//                    if (i == PreparedStatement.EXECUTE_FAILED) {
//                        // Обработка ошибок, например, если добавление не удалось
//                        throw new SQLException("Ошибка при добавлении записи в таблицу field_values.");
//                    }
//                }
//
//                connection.commit();  // Коммитим изменения, если все успешно
//                System.out.println("Все записи успешно добавлены в таблицу field_values.");
//            } catch (SQLException e) {
//                connection.rollback();  // Откатываем изменения в случае ошибки
//                e.printStackTrace();
//                System.err.println("Ошибка при добавлении записи в таблицу field_values: " + e.getMessage());
//            } finally {
//                connection.setAutoCommit(true);  // Включаем автоматический коммит для следующих операций
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Ошибка при подключении к базе данных: " + e.getMessage());
//        }
//    }
//
//    // Проверяем, существует такое или нет
//    public boolean isUserInstance(int instance) {
//        String sql = "SELECT * FROM field_values WHERE form_instance_id = ?";
//
//        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, instance);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                return resultSet.next(); // Возвращает true, если есть хотя бы одна запись
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false; // В случае ошибки
//        }
//    }
}
