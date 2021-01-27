package com.epam.jt.name.dao;

public class SQLConstants {
    private SQLConstants() {
        //hello
    }

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MAIL = "mail";
    public static final String USER_FINE = "fine";
    public static final String USER_ROLE = "role";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login= ?;";
    public static String SELECT_ALL_USERS = "select * from users";
    public static String SELECT_ALL_BOOKS = "SELECT * FROM books";
    public static String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?;";
    public static String SQL_ADD_NEW_USER = "insert into users (username, password, mail, fine, role) values (?, ?, ?, ?, ?);";
    public static String SQL_ADD_NEW_BOOK = "insert into books (title, author, ISBN, publisher, number) values (?, ?, ?, ?, ?);";

}
