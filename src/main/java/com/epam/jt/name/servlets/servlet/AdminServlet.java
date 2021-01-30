package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.domain.Book;
import com.epam.jt.name.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private static UserDao userDao;
    private static String usrId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = UserDao.getInstance();
        usrId = req.getParameter("id");
        User user = userDao.get(Long.parseLong(usrId));
        req.setAttribute("usr", user);
        req.setAttribute("id", usrId);
        req.getRequestDispatcher("/edit_user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDao = UserDao.getInstance();
        User user = userDao.get(Long.parseLong(usrId));

        System.out.println("FINE " + req.getParameter("fine"));
        user.setFine(Double.parseDouble(req.getParameter("fine")));
        user.setRole(req.getParameter("role"));
        if (req.getParameter("isBanned") != null)
            user.setBanned(req.getParameter("isBanned").equals("1"));
        else user.setBanned(false);
        System.out.println("USER ID: " + usrId);

        userDao.update(user);
        System.out.println("UPDATE USER: " + user);
        resp.sendRedirect("/library/menu");
    }
}
