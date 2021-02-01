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
        Context context;
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            con = dataSource.getConnection();
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return con;
    }

    T get(long id);

    List<T> getAll() throws SQLException;

    void save(T t);

    void update(T t);

    void delete(T t);
}