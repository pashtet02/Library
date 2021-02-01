package com.epam.jt.name.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
        Connection con = null;
        Context context = null;
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
        } catch (NamingException e) {
            e.printStackTrace();
        }

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