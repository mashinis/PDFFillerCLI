package ru.mashinis.dao.dell;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.dell.FormFieldsDao;
import ru.mashinis.model.Field;
import ru.mashinis.model.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Класс должен получать id нужной формы и отдавать id form_fields, id_fields
public class FormFieldsDaoImpl  {
//    private HikariDataSource dataSource;
//    private Form form;
//
//    public FormFieldsDaoImpl(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//        form = null;
//    }
//
//    // Метод вернет пустой лист, если нет полей формы или вообще нет такой формы
//    // Вернет лист полей формы
//    @Override
//    public List<Field> getByFormId(int formId) {
//        List<Field> formFieldList = new ArrayList<>();
//        String sql = "SELECT f.* FROM form_fields ff\n" +
//                        "JOIN fields f ON ff.field_id = f.id\n" +
//                        "WHERE ff.form_id = ?;";
//
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            // Установка значения formId в PreparedStatement
//            statement.setInt(1, formId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    formFieldList.add(mapResultSetToField(resultSet));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        setForm(formId);
//        return formFieldList;
//    }
//
//    // Установка самой формы, которую заполняем
//    private void setForm(int id) {
//        String sql = "SELECT * FROM forms WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int formId = resultSet.getInt("id");
//                    String formName = resultSet.getString("name");
//                    String createdAt = resultSet.getString("created_at");
//
//                    this.form = new Form(formId, formName, createdAt);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Form getForm() {
//        return form;
//    }
//
//    // Дополнительный метод для отображения ResultSet в объект Field
//    private Field mapResultSetToField(ResultSet resultSet) throws SQLException {
//        int fieldId = resultSet.getInt("id");
//        String fieldName = resultSet.getString("name");
//        String fieldAlias = resultSet.getString("alias");
//        String createdAt = resultSet.getString("created_at");
//        return new Field(fieldId, fieldName, fieldAlias, createdAt);
//    }
}
