package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.DBManager;
import com.epam.jt.name.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookServlet extends HttpServlet {
    private final DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/library/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setISBN(Long.parseLong(req.getParameter("ISBN")));
        //book.setPublishingDate(req.getParameter("pubDate"));
        book.setPublisher(req.getParameter("publisher"));
        book.setNumber(Integer.parseInt(req.getParameter("number")));
        System.out.println(book);
        dbManager.insertBook(book);

        resp.sendRedirect("/library/first");
    }
}
