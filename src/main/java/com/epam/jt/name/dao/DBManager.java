package com.epam.jt.name.dao;

import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.jt.name.dao.SQLConstants.*;


public class DBManager {
    private static DBManager dbManager;
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    // sslMode: since MySQL 8.0.13.
    // for MySQL versions earlier 8.0.13 use useSSL property instead
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/library";
    private static final String URL_PROPERTIES = "sslMode=DISABLED&serverTimezone=UTC";
    private static final String URL_CREDENTIALS = "user=" + USER + "&password=" + PASSWORD;
    private static final String URL = BASE_URL + "?" + URL_PROPERTIES + "&" + URL_CREDENTIALS;
    //jdbc:mysql://localhost:3306/library?sslMode=DISABLED&serverTimezone=UTC&user=root&password=root
    public static synchronized DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private DBManager() {
        // hello everyone
    }

    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return DriverManager.getConnection(properties.getProperty("connection.url"));
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
        }
        return books;
    }

    public boolean isUserExistsByLoginAndPassword(String login, String password){
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, login);
            pstmt.setString(2, password);
            if (pstmt.execute()) {
                    return true;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                throw throwable;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public User getUserByLoginAndPassword(String login, String password) {
        User user = new User();
        try (Connection con = dbManager.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(GET_USER_BY_LOGIN_AND_PASSWORD)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong(ID));
                    user.setUsername(rs.getString(USERNAME));
                    user.setPassword(rs.getString(USER_PASSWORD));
                    user.setFine(rs.getDouble(USER_FINE));
                    user.setMail(rs.getString(USER_MAIL));
                    user.setRole(rs.getString(USER_ROLE));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }

    public void insertUser(User user) throws SQLException {
        ResultSet rs = null;

        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_ADD_NEW_USER,
                    Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getMail());
            pstmt.setDouble(4, user.getFine());
            pstmt.setString(5, user.getRole().toUpperCase(Locale.ROOT));

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close(rs);
        }
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

    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception ex) {
                throw new IllegalStateException("Cannot close " + ac);
            }
        }
    }

    public void insertBook(Book book) {
        ResultSet rs = null;

        try(Connection con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_ADD_NEW_BOOK,
                    Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setLong(3, book.getISBN());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getNumber());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    book.setId(rs.getLong(1));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            close(rs);
        }
    }
}
