package ru.mashinis.dao;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.model.FormSubmissions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FormSubmissionsDao {
    private HikariDataSource dataSource;

    public FormSubmissionsDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int setUserFormIdentifier(FormSubmissions formSubmissions) {
        int newIdentifier = 0;
        String sql = "INSERT INTO form_submissions (user_id, form_id, user_form_identifier) VALUES (?, ?, ?)\n" +
                "  ON CONFLICT (user_id, form_id) \n" +
                "  DO UPDATE \n" +
                "    SET user_form_identifier = form_submissions.user_form_identifier + 1\n" +
                "  RETURNING user_form_identifier";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, formSubmissions.getUserId());
            statement.setInt(2, formSubmissions.getFormId());
            statement.setInt(3, formSubmissions.getUserFormIdentifier());
            newIdentifier = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newIdentifier;
    }
}
