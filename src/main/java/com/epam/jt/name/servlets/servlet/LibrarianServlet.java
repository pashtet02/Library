package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LibrarianServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> users = userDao.getAll();

            request.getSession().setAttribute("usersList", users);

            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}