package com.library.dao;

import com.library.domain.Book;
import com.library.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements Dao<User> {

    private static UserDao userDao;
    private static BookDao bookDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    private UserDao() {
        //hello
    }

    @Override
    public User get(long id) {
        ResultSet rs = null;
        User user = new User();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM users where id=?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(SQLConstants.ID));
                user.setUsername(rs.getString(SQLConstants.USERNAME));
                user.setPassword(rs.getString(SQLConstants.USER_PASSWORD));
                user.setMail(rs.getString(SQLConstants.USER_MAIL));
                user.setFine(rs.getDouble(SQLConstants.USER_FINE));
                user.setRole(rs.getString(SQLConstants.USER_ROLE));
            }
        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Hello");
        } finally {
            close(rs);
        }
        return user;
    }

    public User getUserByLogin(String login) {
        ResultSet rs = null;
        User user = new User();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(SQLConstants.SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(SQLConstants.ID));
                user.setUsername(rs.getString(SQLConstants.USERNAME));
                user.setPassword(rs.getString(SQLConstants.USER_PASSWORD));
                user.setMail(rs.getString(SQLConstants.USER_MAIL));
                user.setFine(rs.getDouble(SQLConstants.USER_FINE));
                user.setRole(rs.getString(SQLConstants.USER_ROLE));
            }

        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Hello");
        } finally {
            close(rs);
        }
        return user;
    }

    public List<Book> getUserBooks(User user) {
        List<Book> books = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLConstants.SELECT_ALL_USER_BOOKS)
        ) {
            preparedStatement.setLong(1, user.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    bookDao = BookDao.getInstance();
                    books.add(bookDao.get(resultSet.getInt(1)));
                }
            }
        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, throwable.getSQLState(), throwable);
        }
        return books;
    }

    public void setBookForUser(User user, Book book) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            //set users_books value
            preparedStatement = con.prepareStatement(SQLConstants.SET_BOOK);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, book.getId());
            preparedStatement.executeUpdate();

            //decrement book.number;
            book.setNumber(book.getNumber() - 1);
            bookDao = BookDao.getInstance();
            bookDao.save(book);

            con.commit();
        } catch (SQLException throwable) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e) {
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, throwable.getSQLState(), throwable);
            } finally {
                Logger logger = Logger.getAnonymousLogger();
                logger.log(Level.SEVERE, throwable.getSQLState(), throwable);
            }
        } finally {
            if (preparedStatement != null){
                close(preparedStatement);
            }
            if (con != null) {
                close(con);
            }
        }
    }

    public static void setBookForUser(Connection con, User user, Book book) {

    }

    public User getUserByLoginAndPassword(String login, String password) {
        User user = new User();
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLConstants.GET_USER_BY_LOGIN_AND_PASSWORD)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong(SQLConstants.ID));
                    user.setUsername(rs.getString(SQLConstants.USERNAME));
                    user.setPassword(rs.getString(SQLConstants.USER_PASSWORD));
                    user.setFine(rs.getDouble(SQLConstants.USER_FINE));
                    user.setMail(rs.getString(SQLConstants.USER_MAIL));
                    user.setRole(rs.getString(SQLConstants.USER_ROLE));
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQLConstants.SELECT_ALL_USERS)) {

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

    @Override
    public void save(User user) {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.SQL_ADD_NEW_USER,
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

    public boolean isUserExistsByLoginAndPassword(String login, String password) {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.GET_USER_BY_LOGIN_AND_PASSWORD,
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


    @Override
    public void update(User user) {

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
            user.setBanned(rs.getBoolean("isBanned"));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(User user) {
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
}