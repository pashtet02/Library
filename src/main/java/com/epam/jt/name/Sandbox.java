package com.epam.jt.name;

import com.epam.jt.name.dao.DBManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Sandbox {
    public static void main(String[] args) {
// users  ==> [ivanov]
        // teams ==> [teamA]
        DBManager dbManager = DBManager.getInstance();
        // Part 1
        try {
            Connection con = dbManager.getConnection();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        // users  ==> [ivanov, petrov, obama]
        System.out.println("===========================");
    }
}
