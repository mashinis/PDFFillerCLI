package ru.mashinis.dao.implement;

import com.zaxxer.hikari.HikariDataSource;
import ru.mashinis.dao.interfaces.Create;
import ru.mashinis.dao.interfaces.Read;
import ru.mashinis.model.Content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ContentDao implements Create<Content>, Read<Content> {
    private HikariDataSource dataSource;

    public ContentDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int create(Content content) {
        int id = 0;
        String sql = "INSERT INTO contents (form_id, user_id, content) VALUES (?, ?, CAST(? AS jsonb)) RETURNING content_id";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, content.getFormId());
            statement.setInt(2, content.getUserId());
            statement.setString(3, content.getContent());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("content_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Optional<Content> getById(int id) {
        String sql = "SELECT * FROM contents WHERE content_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToContent(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Content mapResultSetToContent(ResultSet resultSet) throws SQLException {
        int contentId = resultSet.getInt("content_id");
        int formId = resultSet.getInt("form_id");
        int userId = resultSet.getInt("user_id");
        String content = resultSet.getString("content");
        String createdAt = resultSet.getString("created_at");

        return new Content(contentId, formId, userId, content, createdAt);
    }
}
