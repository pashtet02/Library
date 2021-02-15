package com.library.name.dao;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void get() {
    }

    @Test
    public void getAllReserved() {
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
}