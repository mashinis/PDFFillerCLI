package ru.mashinis.dao.dell;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.dell.Dao;
import ru.mashinis.model.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FormDaoImpl implements Dao<Form> {
    @Override
    public Optional<Form> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Form> getAll() {
        return null;
    }

    @Override
    public void save(Form form) {

    }

    @Override
    public void update(Form form, String[] params) {

    }

    @Override
    public void delete(Form form) {

    }
//    private HikariDataSource dataSource;
//
//    public FormDaoImpl(HikariDataSource dataSource) {
//        this.dataSource = dataSource;
//        initializeDatabase();
//    }
//    private void initializeDatabase() {
//        try (Connection connection = dataSource.getConnection()) {
//            // Здесь вы можете выполнять необходимые операции инициализации базы данных
//            // Например, создание таблицы, если ее нет, и добавление начальных данных
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error initializing the database.", e);
//        }
//    }
//
//    @Override
//    public Optional<Form> getById(int id) {
//        String sql = "SELECT * FROM forms WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, id);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    return Optional.of(mapResultSetToForm(resultSet));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Form> getAll() {
//        List<Form> formList = new ArrayList<>();
//        String sql = "SELECT * FROM forms";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                formList.add(mapResultSetToForm(resultSet));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return formList;
//    }
//
//    // Метод будет использоваться, когда у пользователей будут роли.
//    // Только Модератор и Админ смогут добавлять новые формы в БД
//    @Override
//    public void save(Form form) {
//        String sql = "INSERT INTO forms (name) VALUES (?)";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1, form.getName());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Метод будет использоваться, когда у пользователей будут роли.
//    // Только владелец сможет редактировать название своих форм
//    @Override
//    public void update(Form form, String[] params) {
//        Objects.requireNonNull(form, "Form cannot be null");
//
//        if (params != null && params.length >= 1) {
//            int formId = form.getId();
//            String sql = "UPDATE forms SET name = ? WHERE id = ?";
//            try (Connection connection = dataSource.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(sql)) {
//
//                statement.setString(1, params[0]);
//                statement.setInt(2, formId);
//
//                statement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Метод будет использоваться, когда у пользователей будут роли.
//    // Только владелец сможет удалить свою форму
//    @Override
//    public void delete(Form form) {
//        int formId = form.getId();
//        String sql = "DELETE FROM forms WHERE id = ?";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setInt(1, formId);
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Дополнительный метод для отображения ResultSet в объект Form
//    private Form mapResultSetToForm(ResultSet resultSet) throws SQLException {
//        int formId = resultSet.getInt("id");
//        String formName = resultSet.getString("name");
//        String createdAt = resultSet.getString("created_at");
//        return new Form(formId, formName, createdAt);
//    }
}
