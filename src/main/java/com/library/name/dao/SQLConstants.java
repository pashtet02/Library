package com.library.name.dao;

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

    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE username= ?;";
    public static final String SQL_FIND_USER_BY_MAIL = "SELECT * FROM users WHERE mail= ?;";
    public static final String SQL_ADD_NEW_USER = "insert into users (username, password, firstName, secondName, mail, fine, role, isBanned, userLocale) values (?,?,?, ?, ?, ?, ?, ?,?);";
    public static final String UPDATE_USER_BY_ID = "update users\n" +
            "    set username = ?, password = ?,mail = ?, firstName=?,secondName=?, role = ?,  fine = ?, isBanned = ?, userLocale = ? where id = ?;";
    public static final String SELECT_ALL_USERS = "select * from users";
    public static final String SELECT_ONLY_USERS = "select * from users where role = 'USER'";


    public static final String GET_BOOK_BY_TITLE = "SELECT * FROM books WHERE title = ? AND number > 0;";
    public static final String GET_BOOK_BY_ISBN = "SELECT * FROM books WHERE ISBN = ? AND number > 0;";
    public static final String SELECT_ALL_BOOKS = "SELECT * FROM books";
    public static final String SQL_ADD_NEW_BOOK = "insert into books (title, author, ISBN, publisher,publishingDate, number, language, image, description_ua, description_en) values (?, ?,?, ?, ?,?, ?, ?,?,?);";


    public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
    public static final String SQL_ADD_NEW_ORDER = "insert into orders (user_id, book_id, startDate, returnDate, status, userComment, librarianComment) values(?, ?,?, ?,?,?,?);";

}
