package com.library.name.dao;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    Logger log = Logger.getLogger(Dao.class);

    /**
     * this method get`s a connection from tomcat dataSource
     * @return Connection to DB from DateSource
     * */
    default Connection getConnection() throws SQLException {
        Connection con = null;
        Context context;
        try {
            context = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            con = dataSource.getConnection();

        } catch (NamingException e) {
            log.error("Cannot obtain a connection from the pool", e);
        }
        return con;
        /*Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(properties.getProperty("connection.url"));*/
    }

    T get(long id);

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t);

    void delete(T t);

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