package ru.mashinis.dao.implement;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.Create;
import ru.mashinis.dao.interfaces.Read;
import ru.mashinis.model.Form;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FormDao implements Read<Form>, Create<Form> {
    private HikariDataSource dataSource;

    public FormDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Form> getById(int id) {
        String sql = "SELECT * FROM forms WHERE form_id = ?";
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

    public Set<Integer> getUniqueIds() {
        Set<Integer> uniqueIds = new HashSet<>();
        String sql = "SELECT DISTINCT form_id FROM forms";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("form_id");
                uniqueIds.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uniqueIds;
    }

    public List<Form> getAll() {
        List<Form> formList = new ArrayList<>();
        String sql = "SELECT * FROM forms";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                formList.add(mapResultSetToForm(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formList;
    }

    private Form mapResultSetToForm(ResultSet resultSet) throws SQLException {
        int formId = resultSet.getInt("form_id");
        String formName = resultSet.getString("form_name");
        String createdAt = resultSet.getString("created_at");
        String formPath = resultSet.getString("form_path");

        return new Form(formId, formName, createdAt, formPath);
    }

    @Override
    public int create(Form form) {
        int id = 0;
        String sql = "INSERT INTO forms (form_name, form_path) VALUES (?, ?) RETURNING form_id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, form.getName());
            statement.setString(2, form.getFormPath());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("form_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
