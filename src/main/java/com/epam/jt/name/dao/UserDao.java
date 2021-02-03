package com.epam.jt.name.dao;

import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class UserDao implements Dao<User> {

    private static UserDao userDao;
    private static BookDao bookDao;
    private static final Logger logger = Logger.getLogger(UserDao.class);

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
            logger.info("userDao get() user: " + user.getId());
        } catch (SQLException throwable) {
            logger.error("get() userDao exception: " + throwable.getSQLState(), throwable);
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
                user.setBanned(rs.getInt("isBanned") == 1);
            }
            logger.info("getUserByLogin: " + user.getId() + " " + user.getUsername());
        } catch (SQLException throwable) {
            logger.error("getUserByLogin() userDao exception: " + throwable.getSQLState(), throwable);
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
            logger.error("getUserBooks() userDao exception: " + throwable.getSQLState(), throwable);
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
                //FIX THIS METHOD OR DELETE IT
            } finally {

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
                    user.setBanned(String.valueOf(rs.getInt("isBanned")).equals("0"));
                }
            }
        } catch (SQLException throwable) {
            logger.error("getUserByLoginAndPassword() userDao exception login: {}"
                    + login + throwable.getSQLState(), throwable);
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
            logger.error("getAllUsers() userDao exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.SQL_ADD_NEW_USER,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getMail());
            pstmt.setDouble(4, user.getFine());
            pstmt.setString(5, user.getRole().toUpperCase(Locale.ROOT));
            pstmt.setBoolean(6, user.isBanned());

            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
            logger.info("user saved succesfully login: " + user.getUsername());
        } catch (SQLException throwable) {
            logger.error("save() userDao exception: " + throwable.getSQLState(), throwable);
            throw throwable;
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
                logger.error("isUserExistsByLoginAndPassword() userDao exception: "
                        + throwable.getSQLState(), throwable);
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public void blockOrUnblockUser(User user, int value){
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("UPDATE users SET isBanned= ? where id=?")) {
            preparedStatement.setInt(1, value);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.executeUpdate();
            logger.info("block or unblock user: " + user + " value: " + value);
        } catch (SQLException throwable) {
            logger.error("blockOrUnblockUser() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
    }
    public void updateRoleById(User user, long id){
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "update users set fine = ?, role = ?, isBanned = ? where id =?")) {
            preparedStatement.setDouble(1, user.getFine());
            preparedStatement.setString(2, user.getRole());
            preparedStatement.setBoolean(3, user.isBanned());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            logger.debug("role updated userid: {} {}" + user.getId() + "role: " + user.getRole());
        } catch (SQLException throwable) {
            logger.error("updateRoleById() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
    }

    //updates by username
    @Override
    public void update(User user) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLConstants.UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getMail());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setDouble(5, user.getFine());
            preparedStatement.setInt(6, user.isBanned() ? 1 : 0);
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
            logger.debug("updated userid: " + user.getId());
        } catch (SQLException throwable) {
            logger.error("update() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
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
            user.setBanned(String.valueOf(rs.getInt("isBanned")).equals("0"));
            logger.debug("mapUser userid: " + user.getId());
        } catch (SQLException throwable) {
            logger.error("mapUser() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
        return user;
    }

    @Override
    public void delete(User user) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("delete from users where username=?")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            logger.error("delete() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
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