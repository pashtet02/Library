package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.entity.User;

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
        /*userDao = UserDao.getInstance();
        usrId = req.getParameter("id");
        User user = userDao.get(Long.parseLong(usrId));
        req.setAttribute("usr", user);
        req.setAttribute("id", usrId);

        req.getRequestDispatcher("/edit_user.jsp").forward(req, resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*userDao = UserDao.getInstance();
        User user = userDao.get(Long.parseLong(usrId));

        System.out.println("FINE " + req.getParameter("fine"));
        if (req.getParameter("fine") != null && !req.getParameter("fine").isEmpty())
            user.setFine(Double.parseDouble(req.getParameter("fine")));
        if (req.getParameter("role") != null && !req.getParameter("role").isEmpty())
            user.setRole(req.getParameter("role") );
        if (req.getParameter("isBanned") != null && !req.getParameter("isBanned").isEmpty()){
            userDao.blockOrUnblockUser(user, 1);
            user.setBanned(req.getParameter("isBanned").equals("on"));
        } else user.setBanned(false);
        System.out.println("USER ID: " + usrId);
        userDao.update(user);
        System.out.println("UPDATE USER: " + user);
        resp.sendRedirect("/library/first/menu");*/
    }
}
