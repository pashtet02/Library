package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.domain.Book;
import com.epam.jt.name.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class UserServlet extends HttpServlet {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private HttpSession session;
    private final BookDao bookDao = BookDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        try {
            List<Book> books = bookDao.getAll();
            System.out.println("BOOKS" + books);

            session.setAttribute("bookList", books);

            request.getRequestDispatcher("/books.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter(LOGIN));
        user.setPassword(req.getParameter(PASSWORD));


        user.setMail(req.getParameter("mail"));
        user.setFine(0.0);
        user.setRole("USER");

        req.getSession().setAttribute(LOGIN, req.getParameter(LOGIN));
        req.getSession().setAttribute(PASSWORD, req.getParameter(PASSWORD));
        req.getSession().setAttribute("role", user.getRole());
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}