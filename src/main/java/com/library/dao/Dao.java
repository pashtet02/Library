package com.library.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface Dao<T> {

    default Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return DriverManager.getConnection(properties.getProperty("connection.url"));
    }

    T get(long id);

    List<T> getAll() throws SQLException;

    void save(T t);

    void update(T t);

    void delete(T t);
}