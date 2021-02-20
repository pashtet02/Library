/*
package com.library.name.dao;

import com.library.name.entity.Order;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoTest {
*/
/*    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=sa;password=sa;";
    private static final String USER = "sa";
    private static final String PASS = "sa";

    @Spy
    private static OrderDao orderDao;

    @BeforeClass
    public static  void beforeTest() throws SQLException {
        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
        orderDao = OrderDao.getInstance();
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "DROP TABLE IF EXISTS users; " +
                    "DROP TABLE IF EXISTS books; " +
                    "DROP TABLE IF EXISTS orders; " +
                    "DROP TABLE IF EXISTS reviews; ";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "-- -----------------------------------------------------\n" +
                    "CREATE SCHEMA IF NOT EXISTS library4;\n" +
                    "USE library4;\n" +
                    "\n" +
                    "-- -----------------------------------------------------\n" +
                    "-- Table library3.books\n" +
                    "-- -----------------------------------------------------\n" +
                    "CREATE TABLE IF NOT EXISTS library4.books\n" +
                    "(\n" +
                    "    id             INT          NOT NULL AUTO_INCREMENT,\n" +
                    "    title          VARCHAR(64)  NOT NULL,\n" +
                    "    author         VARCHAR(64)  NOT NULL,\n" +
                    "    ISBN           VARCHAR(13)  NOT NULL,\n" +
                    "    publisher      VARCHAR(64)  NULL DEFAULT NULL,\n" +
                    "    publishingDate DATE         NULL DEFAULT NULL,\n" +
                    "    number         INT UNSIGNED NULL DEFAULT NULL,\n" +
                    "    language       VARCHAR(45)  NULL DEFAULT NULL,\n" +
                    "    image          VARCHAR(128) NULL DEFAULT NULL,\n" +
                    "    description_ua VARCHAR(512) NULL DEFAULT NULL,\n" +
                    "    description_en VARCHAR(512) NULL DEFAULT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "-- -----------------------------------------------------\n" +
                    "-- Table library3.users\n" +
                    "-- -----------------------------------------------------\n" +
                    "CREATE TABLE IF NOT EXISTS library4.users\n" +
                    "(\n" +
                    "    id         INT                                 NOT NULL AUTO_INCREMENT,\n" +
                    "    username   VARCHAR(20)                         NOT NULL,\n" +
                    "    password   VARCHAR(72)                         NOT NULL,\n" +
                    "    mail       VARCHAR(45)                         NOT NULL,\n" +
                    "    fine       DECIMAL(7, 2) UNSIGNED              NULL     DEFAULT NULL,\n" +
                    "    role       ENUM ('USER', 'LIBRARIAN', 'ADMIN') NOT NULL,\n" +
                    "    isBanned   TINYINT(3)         NULL                              DEFAULT NULL,\n" +
                    "    userLocale VARCHAR(2)                          NULL     DEFAULT NULL,\n" +
                    "    firstName  VARCHAR(45)                         NULL     DEFAULT NULL,\n" +
                    "    secondName VARCHAR(45)                         NULL     DEFAULT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "  \n" +
                    "\n" +
                    "\n" +
                    "-- -----------------------------------------------------\n" +
                    "-- Table library3.orders\n" +
                    "-- -----------------------------------------------------\n" +
                    "CREATE TABLE IF NOT EXISTS library4.orders\n" +
                    "(\n" +
                    "    id               INT                                                                                  NOT NULL AUTO_INCREMENT,\n" +
                    "    user_id          INT                                                                                  NOT NULL,\n" +
                    "    book_id          INT                                                                                  NOT NULL,\n" +
                    "    startDate        TIMESTAMP(5)                                                                         NOT NULL,\n" +
                    "    returnDate       DATE                                                                                 NULL DEFAULT NULL,\n" +
                    "    status           ENUM ('RESERVED', 'APPROVED', 'READING_HALL', 'REFUSED', 'UNTERMINATED', 'RETURNED') NOT NULL,\n" +
                    "    userComment      VARCHAR(250)                                                                         NULL DEFAULT NULL,\n" +
                    "    librarianComment VARCHAR(250)                                                                         NULL DEFAULT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "-- ------------------------------------------\u0013-----------\n" +
                    "-- Table library3.reviews\n" +
                    "-- -----------------------------------------------------\n" +
                    "CREATE TABLE IF NOT EXISTS library4.reviews\n" +
                    "(\n" +
                    "    id           INT          NOT NULL AUTO_INCREMENT,\n" +
                    "    user_id      INT          NOT NULL,\n" +
                    "    book_id      INT          NOT NULL,\n" +
                    "    mark         TINYINT      NOT NULL,\n" +
                    "    user_comment VARCHAR(512) NOT NULL,\n" +
                    "    username     VARCHAR(45)  NULL DEFAULT NULL,\n" +
                    "    bookTitle    VARCHAR(45)  NULL DEFAULT NULL,\n" +
                    "    creationDate TIMESTAMP(3) NOT NULL,\n" +
                    "    PRIMARY KEY (id, user_id, book_id)\n" +
                    ");";
            statement.executeUpdate(sql);
        }
    }*//*


    @Test
    public void get() {
        OrderDao orderDao = OrderDao.getInstance();
        Order order = orderDao.get(62);
        Assert.assertNotNull(order);
    }

    @Test
    public void getAllReserved() throws SQLException {
        List<Order> reservedOrders;
        OrderDao orderDao = OrderDao.getInstance();
        reservedOrders = orderDao.getAllReserved();
        Assert.assertTrue(reservedOrders.size() > 0);
        boolean result = false;
        for (Order order: reservedOrders) {
            result = order.getStatus().equals("RESERVED");
        }
        Assert.assertTrue(result);
    }

    @Test
    public void setOrderStatus() {
    }

    @Test
    public void setReturnDate() {
    }

    @Test
    public void setLibrarianComment() {
    }

    @Test
    public void getSomeOrders() {
    }

    @Test
    public void isOrderExists() {
        OrderDao orderDao = OrderDao.getInstance();
        Assert.assertTrue(orderDao.isOrderExists(16, 11));
    }

    @Test
    public void getAll() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getByBookId() {
    }

    @Test
    public void getByUserId() {
    }

    @Test
    public void getNotReturnedByUserId() {
    }
}*/
