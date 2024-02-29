package ru.mashinis.dbmanager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseManager {
    private static HikariDataSource dataSource;

    static {
        Properties properties = loadProperties();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));

        dataSource = new HikariDataSource(config);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("properties/application.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("Unable to find properties file.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file.", e);
        }
        return properties;
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}

