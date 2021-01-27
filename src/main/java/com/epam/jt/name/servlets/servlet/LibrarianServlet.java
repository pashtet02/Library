package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.DBManager;
import com.epam.jt.name.domain.Book;
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
        DBManager dbManager = DBManager.getInstance();
        try {
            List<User> users = dbManager.findAllUsers();
            List<Book> books = dbManager.findAllBooks();
            System.out.println("BOOKS" + books);


            request.getSession().setAttribute("usrLst", users);
            request.getSession().setAttribute("bookList", books);

            request.getRequestDispatcher("/books.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}