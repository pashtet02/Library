package com.epam.jt.name.dao;

import com.epam.jt.name.dao.SQLConstants;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.jt.name.dao.SQLConstants.SELECT_ALL_BOOKS;
import static com.epam.jt.name.dao.SQLConstants.SELECT_ALL_USERS;


public class DBManager {
    private static DBManager dbManager;;
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    // sslMode: since MySQL 8.0.13.
    // for MySQL versions earlier 8.0.13 use useSSL property instead
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/library";
    private static final String URL_PROPERTIES = "sslMode=DISABLED&serverTimezone=UTC";
    private static final String URL_CREDENTIALS = "user=" + USER + "&password=" + PASSWORD;
    private static final String URL = BASE_URL + "?" + URL_PROPERTIES + "&" + URL_CREDENTIALS;

    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private DBManager() {
        // hello everyone
    }

    public Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(URL);
    }

    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "MESSAGE");
            throw ex;
        }catch (IOException e){
            e.printStackTrace();
        }
        return users;
    }

    public List<Book> findAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKS)) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }
        } catch (SQLException ex) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "MESSAGE");
            throw ex;
        }catch (IOException e){
            e.printStackTrace();
        }
        return books;
    }

    private Book mapBook(ResultSet rs) {
        Book book = new Book();
        try {
            book.setId(rs.getLong("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPublisher(rs.getString("publisher"));
            book.setISBN(rs.getLong("ISBN"));
            book.setNumber(rs.getInt("number"));
            book.setPublishingDate(rs.getDate("publishingDate"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return book;
    }

    private User mapUser(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setMail(rs.getString("mail"));
            user.setFine(rs.getInt("fine"));
            user.setRole(rs.getString("role"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }
}
