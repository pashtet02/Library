package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.domain.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BookServlet extends HttpServlet {
    private final BookDao bookDao = BookDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/library/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utd-8");
        resp.setCharacterEncoding("utf-8");
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setISBN(Long.parseLong(req.getParameter("ISBN")));
        book.setPublisher(req.getParameter("publisher"));
        book.setNumber(Integer.parseInt(req.getParameter("number")));
        System.out.println(book);
        try {
            bookDao.save(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.sendRedirect("/library/first");
    }
}
