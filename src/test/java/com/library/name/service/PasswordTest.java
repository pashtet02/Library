package com.library.name.service;

import org.junit.Assert;
import org.junit.Test;

public class PasswordTest {

    @Test
    public void hashPassword() {
        String password = "25012002";
        String hashPass = Password.hashPassword(password);
        Assert.assertNotEquals(hashPass, password);
    }

    @Test
    public void checkPassword() {
        String password = "25012002";
        String hashPass = Password.hashPassword(password);
        Assert.assertTrue(Password.checkPassword(password, hashPass));
    }
}