/*
package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LibrarianServlet extends HttpServlet {
    private static BookDao bookDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        */
/*UserDao userDao = UserDao.getInstance();
        try {
            List<User> users = userDao.getAll();

            request.getSession().setAttribute("usersList", users);

            request.getRequestDispatcher("/users.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*//*

    }

    */
/*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        System.out.println(req.getParameter("title"));
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setISBN(Long.parseLong(req.getParameter("ISBN")));
        book.setPublisher(req.getParameter("publisher"));
        book.setNumber(Integer.parseInt(req.getParameter("number")));
        book.setLanguage(req.getParameter("language"));


        bookDao = BookDao.getInstance();
        try {
            bookDao.save(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("ADD BOOK " + book);
        resp.sendRedirect("/library/catalog");
    }*//*


}*/
