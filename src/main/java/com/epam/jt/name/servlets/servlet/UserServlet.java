package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.domain.Book;
import com.epam.jt.name.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private final UserDao userDao = UserDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Book> books = userDao.getUserBooks((User) session.getAttribute("user"));
        System.out.println("books: " + books);
        session.setAttribute("userBookList", books);

        request.getRequestDispatcher("/books.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));
        user.setMail(req.getParameter("mail"));
        user.setFine(0.0);
        user.setRole("USER");

        userDao.save(user);

        req.getSession().setAttribute(LOGIN, req.getParameter(LOGIN));
        req.getSession().setAttribute(PASSWORD, req.getParameter(PASSWORD));
        req.getSession().setAttribute("role", user.getRole());
        req.getSession().setAttribute("user", user);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}