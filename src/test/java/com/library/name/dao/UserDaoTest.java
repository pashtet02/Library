package com.library.name.dao;

import com.library.name.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class UserDaoTest {
    private final UserDao userDao = UserDao.getInstance();

    User user = new User();

    @Before
    public void setUp(){
        user = userDao.getUserByLogin("TestUser");
    }

    @Test
    public void get() {
        User getTest = userDao.get(user.getId());
        Assert.assertNotNull(getTest.getFirstName());
        Assert.assertNotNull(getTest.getMail());
    }

    @Test
    public void getUserByLogin() {
        User user;
        user = userDao.getUserByLogin("TestUser");
        Assert.assertNotNull(user.getUsername());
        Assert.assertNotNull(user.getMail());
    }

    @Test
    public void getUserByEmail() {
        User getMailTest;
        getMailTest = userDao.getUserByEmail(user.getMail());
        Assert.assertEquals("TestUser",getMailTest.getUsername());
        Assert.assertEquals("test@test.com",getMailTest.getMail());
    }

    @Test
    public void getAllUsers() throws SQLException {
        List<User> users;
        users = userDao.getAllUsers();
        boolean result = false;
        for (User user: users) {
            result = user.getRole().equals("USER");
        }
        Assert.assertTrue(result);
    }

    @Test
    public void getAll() throws SQLException {
        List<User> users;
        users = userDao.getAll();
        Assert.assertTrue(users.size() > 0);
    }

    @Test
    public void getAllOrderBy() {
        List<User> list;
        list = userDao.getAllOrderBy("id");
        Assert.assertTrue(list.get(0).getId() < list.get(1).getId());
    }

    @Test
    public void getAllUsersOrderBy() {
        List<User> list;
        list = userDao.getAllOrderBy("id");
        Assert.assertTrue(list.get(0).getId() < list.get(1).getId());
        boolean result = false;
        for (User user: list) {
            result = user.getRole().equals("USER");
        }
        Assert.assertTrue(result);
    }

    @Test
    public void save() throws SQLException {
        user.setId(-10);
        user.setUsername("JUNIT1");
        user.setFine(10.10);
        user.setMail("hehehe1@test.com");
        user.setPassword("$2a$12$4oWNJXreFx54LLPfoLVH4u2.AHmdikLeOQvDstOmw1Ph85Fpj8NCe");
        user.setUserLocale("ua");
        user.setSecondName("Khodachok");
        user.setFirstName("Pavlo");
        user.setBanned(false);
        userDao.save(user);

        user = userDao.getUserByLogin("JUNIT1");
        Assert.assertTrue(user.getId()>0);
        Assert.assertNotNull(user.getUsername());
        userDao.delete(user);
    }

    @Test
    public void update() {
        Random rand = new Random();
        double randNum = rand.nextDouble();
        user.setFine(randNum);
        userDao.update(user);
        Assert.assertEquals(randNum, user.getFine(), 0.00001);
        user.setFine(0.0);
        userDao.update(user);
    }

    //IT WORKS
    @Test
    public void delete() throws SQLException {
        user.setId(-10);
        user.setUsername("ILONMASK");
        user.setFine(10.10);
        user.setMail("ILONMASK@test.com");
        user.setPassword("$2a$12$4oWNJXreFx54LLPfoLVH4u2.AHmdikLeOQvDstOmw1Ph85Fpj8NCe");
        user.setUserLocale("ua");
        user.setSecondName("ILONMASK");
        user.setFirstName("ILONMASK");
        user.setBanned(false);
        userDao.save(user);

        int expected = userDao.getAll().size() -1;
        userDao.delete(user);
        Assert.assertEquals(expected, userDao.getAll().size());
        user = userDao.getUserByLogin("ILONMASK");
        Assert.assertNull(user.getUsername());
    }

    @After
    public void cleanUp(){
        user = null;
    }
}