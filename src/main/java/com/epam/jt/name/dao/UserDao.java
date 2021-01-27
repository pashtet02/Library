package com.epam.jt.name.dao;

import com.epam.jt.name.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.jt.name.dao.SQLConstants.*;

public class UserDao implements Dao<User> {

    private List<User> users = new ArrayList<>();
    private static UserDao userDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }
    private UserDao(){
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
                user.setId(rs.getLong(ID));
                user.setUsername(rs.getString(USERNAME));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setMail(rs.getString(USER_MAIL));
                user.setFine(rs.getDouble(USER_FINE));
                user.setRole(rs.getString(USER_ROLE));
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
             PreparedStatement statement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            rs = statement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong(ID));
                user.setUsername(rs.getString(USERNAME));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setMail(rs.getString(USER_MAIL));
                user.setFine(rs.getDouble(USER_FINE));
                user.setRole(rs.getString(USER_ROLE));
            }

        } catch (SQLException throwable) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Hello");
        } finally {
            close(rs);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        ResultSet rs = null;

        try (Connection con = getConnection();
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

    @Override
    public void update(User user, String[]params) {
        user.setName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        user.setEmail(Objects.requireNonNull(
                params[1], "Email cannot be null"));

        users.add(user);
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

    @Override
    public void delete(User user) {
        users.remove(user);
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