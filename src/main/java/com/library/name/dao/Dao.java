package com.library.name.dao;

import org.apache.log4j.Logger;

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

public interface Dao<T> {
    Logger log = Logger.getLogger(Dao.class);


    default Connection getConnection() throws SQLException {
        Connection con = null;
        Context context;
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            con = dataSource.getConnection();

        } catch (NamingException e) {
            log.error("Cannot obtain a connection from the pool", e);
            e.printStackTrace();
        }
        return con;
        /*Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            java.util.logging.Logger logger = java.util.logging.Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return DriverManager.getConnection(properties.getProperty("connection.url"));*/
    }

    T get(long id);

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t);

    void delete(T t);


    default void commitAndClose(Connection con) {
        try {
            if (con != null){
                con.commit();
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    default void rollbackAndClose(Connection con) {
        try {
            if (con != null){
                con.rollback();
                con.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}