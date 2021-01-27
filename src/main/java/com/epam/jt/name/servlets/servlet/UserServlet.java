package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.DBManager;
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
    private HttpSession session;
    private final DBManager dbManager = DBManager.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        try {
            List<Book> books = dbManager.findAllBooks();
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
        user.setUsername(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setMail("heheheh");
        user.setFine(0.0);
        user.setRole("USER");
        try {
            System.out.println(user);
            dbManager.insertUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getSession().setAttribute("login", req.getParameter("login"));
        req.getSession().setAttribute("password", req.getParameter("password"));
        req.getRequestDispatcher("/test.html").forward(req, resp);
    }
}