package com.library.name.dao;

import com.library.name.entity.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OrderDao implements Dao<Order> {
    private static OrderDao orderDao;
    private static final Logger logger = Logger.getLogger(OrderDao.class);

    public static synchronized OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }

    private OrderDao() {
        // hello everyone
    }

    public boolean approveOrder(long orderId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

             preparedStatement =
                    con.prepareStatement("update orders set status = 'APPROVED' where id = ?");
            preparedStatement.setLong(1, orderId);
            preparedStatement.executeUpdate();

            Order order = get(orderId);

            BookDao bookDao = BookDao.getInstance();
            bookDao.decrementNumberBook(order.getBookId());

            //Set return date to today
            long millis=System.currentTimeMillis();
            Date date=new Date(millis);
            order.setReturnDate(date);
            update(order);

            con.commit();
            result = true;
            log.info("Book returned successfully + " + result);
        } catch (SQLException e) {
            if (con != null) {
                rollbackAndClose(con);

            }
            log.error(e.getMessage()  + e.getSQLState());
        } finally {
            close(preparedStatement);
            close(con);
        }
        return result;
    }

    @Override
    public Order get(long id) {
        ResultSet rs = null;
        Order order = null;

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement("select * from orders where id= ?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                order = mapOrder(rs);
            }

        } catch (SQLException throwable) {
            log.error("get()" + throwable.getSQLState() + throwable.getMessage());
        } finally {
            close(rs);
        }
        return order;
    }

    public List<Order> getAllReserved() throws SQLException {
        Connection con = getConnection();
        List<Order> orders;
        orders = getAllOrders(con, "SELECT * FROM orders WHERE status= 'RESERVED'");
        return orders;
    }

    public void setOrderStatus(long id, String status) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement("update orders set status = ? where id = ?")) {
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            log.error("setOrder Status:" + throwable.getSQLState() + throwable.getMessage());
        }
    }

    public void setReturnDate(long id, Date date) {
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE orders SET returnDate=? WHERE id = ?;")) {
            preparedStatement.setDate(1, date);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            logger.error(throwable.getSQLState(), throwable);
        }
    }

    public void setLibrarianComment(long id, String comment) {
        if (comment.length() > 250)
            throw new IllegalArgumentException("Comment is too long (max length 250 characters)");
        try (Connection con = getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "UPDATE orders SET librarianComment=? WHERE id = ?;")) {
            preparedStatement.setString(1, comment);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            logger.error(throwable.getSQLState(), throwable);
        }
    }

    public List<Order> getSomeOrders(int start, int numberOfOrders) throws SQLException {
        Connection con = getConnection();
        List<Order> orders;
        orders = getAllOrders(con, "select * from orders limit " + (start - 1) * numberOfOrders + "," + numberOfOrders);
        logger.debug("getSome Orders: " + orders.size());
        return orders;
    }

    private static List<Order> getAllOrders(Connection con, String query) throws SQLException {
        List<Order> orders = new ArrayList<>();

        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                orders.add(mapOrder(rs));
            }
            if (!orders.isEmpty()) {
                logger.info("getAllOrders() get successfully number of orders: " + orders.size());
            }
        } catch (SQLException ex) {
            logger.error("getSomeOrders method exception: " + ex.getSQLState(), ex);
            throw ex;
        }
        return orders;
    }

    public boolean isOrderExists(long userId, long bookId) {
        ResultSet rs = null;
        Order order = null;

        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "select * from orders where user_id= ? AND book_id=?")) {
            statement.setLong(1, userId);
            statement.setLong(2, bookId);

            rs = statement.executeQuery();
            while (rs.next()) {
                order = mapOrder(rs);
            }
            log.info("Order exists: " + (order != null));
        } catch (SQLException throwable) {
            log.error("isOrderExists()" + throwable.getSQLState() + throwable.getMessage());
        } finally {
            close(rs);
        }
        return order != null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders;

        Connection con = getConnection();
        orders = getAllOrders(con, SQLConstants.SELECT_ALL_ORDERS);
        return orders;
    }

    @Override
    public void save(Order order) throws SQLException {
        ResultSet rs = null;
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQLConstants.SQL_ADD_NEW_ORDER,
                     Statement.RETURN_GENERATED_KEYS)) {
            setOrderToPrepStmt(order, pstmt);
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getLong(1));
                }
            }
            logger.info("order saved successfully order user_id: " + order.getUserId() + "book_id " + order.getBookId());
        } catch (SQLException throwable) {
            logger.error("Book save() error: " + throwable.getSQLState(), throwable);
            throw throwable;
        } finally {
            close(rs);
        }
    }

    @Override
    public void update(Order order) {
        throw new IllegalArgumentException("pishov naher!");
    }

    private void setOrderToPrepStmt(Order order, PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setLong(1, order.getUserId());
        preparedStatement.setLong(2, order.getBookId());
        preparedStatement.setTimestamp(3, order.getStartDate());
        preparedStatement.setDate(4, order.getReturnDate());
        preparedStatement.setString(5, order.getStatus());
        preparedStatement.setString(6, order.getUserComment());
        preparedStatement.setString(7, order.getLibrarianComment());
    }

    @Override
    public void delete(Order order) {
        //hello
    }

    private static Order mapOrder(ResultSet rs) {
        Order order = new Order();
        try {
            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setBookId(rs.getLong("book_id"));
            order.setStartDate(rs.getTimestamp("startDate"));
            order.setReturnDate(rs.getDate("returnDate"));
            order.setStatus(rs.getString("status"));
            order.setUserComment(rs.getString("userComment"));
            order.setLibrarianComment(rs.getString("librarianComment"));
        } catch (SQLException throwable) {
            logger.error("update order method exception: " + throwable.getSQLState(), throwable);
        }
        return order;
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

    public List<Order> getByBookId(Long bookId) {
        List<Order> orders = null;

        try {
            Connection con = getConnection();
            orders = getAllOrders(con, "select * from orders where book_id = " + bookId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public List<Order> getByUserId(Long userId) {
        List<Order> orders = null;
        try {
            Connection con = getConnection();
            orders = getAllOrders(con, "select * from orders where user_id = " + userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    public double countUserFineByUserId(long userId) {
        ResultSet rs = null;
        List<Order> unterminatedOrders = new ArrayList<>();
        int totalDays = 0;
        double fine = 0.0;
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "select * from orders where user_id = ? " +
                             "AND DATEDIFF(curdate(), returnDate) > 0 and status = 'APPROVED' or 'UNTERMINATED';")) {
            statement.setLong(1, userId);

            rs = statement.executeQuery();
            while (rs.next()) {
                unterminatedOrders.add(mapOrder(rs));
            }
            logger.info("unterminaded list: " + unterminatedOrders);

            for (Order order : unterminatedOrders) {
                totalDays += countUnterminatedDays(order.getId());
            }

            //Calculating fine
            if (totalDays > 0){
                fine = totalDays * 3.0;
            }
            logger.info("FINE: " + fine);
        } catch (SQLException throwable) {
            logger.error("userFINE()" + throwable.getSQLState() + throwable.getMessage());
        } finally {
            close(rs);
        }
        return fine;
    }

    private int countUnterminatedDays(long orderId) {
        ResultSet rs = null;
        int result = 0;
        try (Connection con = getConnection();
             PreparedStatement statement = con.prepareStatement(
                     "SELECT SUM(DATEDIFF(CURDATE(), returnDate)) FROM orders where id = ?")) {
            statement.setLong(1, orderId);

            rs = statement.executeQuery();
            while (rs.next()) {
                boolean bool = Integer.parseInt(rs.getString(1)) > 0;
                if (bool) {
                    result += Integer.parseInt(rs.getString(1));
                }
            }
            logger.info("countUnterminatedDays " + result);
        } catch (SQLException throwable) {
            logger.error("countUnterminatedDays()" + throwable.getSQLState() + throwable.getMessage());
        } finally {
            close(rs);
        }
        return result;
    }
}
