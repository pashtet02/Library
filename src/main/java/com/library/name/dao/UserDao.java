package com.library.name.dao;

import com.library.name.entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDao implements Dao<User> {

    private static UserDao userDao;
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
                setUserFromResSet(rs, user);
            }
            logger.info("userDao get() user: " + user.getId());
        } catch (SQLException throwable) {
            logger.error("get() userDao exception: " + throwable.getSQLState(), throwable);
        } finally {
            close(rs);
        }
        return user;
    }

    private void setUserFromResSet(ResultSet rs, User user) throws SQLException {
        user.setId(rs.getLong(SQLConstants.ID));
        user.setUsername(rs.getString(SQLConstants.USERNAME));
        user.setPassword(rs.getString(SQLConstants.USER_PASSWORD));
        user.setFirstName(rs.getString("firstName"));
        user.setSecondName(rs.getString("secondName"));
        user.setMail(rs.getString(SQLConstants.USER_MAIL));
        user.setFine(rs.getDouble(SQLConstants.USER_FINE));
        user.setRole(rs.getString(SQLConstants.USER_ROLE));
        user.setBanned(rs.getInt("isBanned") == 1);
        user.setUserLocale(rs.getString("userLocale"));
    }

    /**
     * @param login param to search from BD
     * @return user by login from table users
     * */
    public User getUserByLogin(String login) {
        ResultSet rs = null;
        User user = new User();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(SQLConstants.SQL_FIND_USER_BY_LOGIN)) {
            statement.setString(1, login);
            rs = statement.executeQuery();
            while (rs.next()) {
                setUserFromResSet(rs, user);
            }
            logger.info("getUserByLogin: " + user.getId() + " " + user.getUsername());
        } catch (SQLException throwable) {
            logger.error("getUserByLogin() userDao exception: " + throwable.getSQLState(), throwable);
        } finally {
            close(rs);
        }
        return user;
    }

    public User getUserByEmail(String email) {
        ResultSet rs = null;
        User user = new User();

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(SQLConstants.SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, email);
            rs = statement.executeQuery();
            while (rs.next()) {
                setUserFromResSet(rs, user);
            }
            logger.info("getUserByEmail: " + user.getId() + " " + user.getUsername());
        } catch (SQLException throwable) {
            logger.error("getUserByEmail() userDao exception: " + throwable.getSQLState(), throwable);
        } finally {
            close(rs);
        }
        return user;
    }

    /**
     * @return List of users with role 'USER' from BD
     * */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SQLConstants.SELECT_ONLY_USERS)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {
            logger.error("getONLYUsers() userDao exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return users;
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

    public List<User> getAllOrderBy(String orderParam) {
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from users order by " + orderParam)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {
            logger.error(ex.getSQLState(), ex);
        }
        return users;
    }

    public List<User> getAllUsersOrderBy(String orderParam){
        List<User> users = new ArrayList<>();

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from users where role = 'USER' order by " + orderParam)) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }
        } catch (SQLException ex) {
            logger.error(ex.getSQLState(), ex);
        }
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        ResultSet rs = null;

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.SQL_ADD_NEW_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            int k = 1;
            pstmt.setString(k++, user.getUsername());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getSecondName());

            pstmt.setString(k++, user.getMail());
            pstmt.setDouble(k++, user.getFine());
            pstmt.setString(k++, user.getRole().toUpperCase(Locale.ROOT));
            pstmt.setBoolean(k++, user.isBanned());
            pstmt.setString(k, user.getUserLocale());

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

    @Override
    public void update(User user) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLConstants.UPDATE_USER_BY_ID)) {
            int k = 1;
            preparedStatement.setString(k++, user.getUsername());
            preparedStatement.setString(k++, user.getPassword());
            preparedStatement.setString(k++, user.getMail());
            preparedStatement.setString(k++, user.getFirstName());
            preparedStatement.setString(k++, user.getSecondName());
            preparedStatement.setString(k++, user.getRole());
            preparedStatement.setDouble(k++, user.getFine());
            preparedStatement.setInt(k++, user.isBanned() ? 1 : 0);
            preparedStatement.setString(k++, user.getUserLocale());
            preparedStatement.setLong(k, user.getId());
            preparedStatement.executeUpdate();
            logger.debug("updated userid: " + user.getId());
           log.error("UPDATED USER !!!!" + user);
        } catch (SQLException throwable) {
            logger.error("update() userDao exception: "
                    + throwable.getSQLState(), throwable);
        }
    }


    private User mapUser(ResultSet rs) {
        User user = new User();
        try {
            setUserFromResSet(rs, user);
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