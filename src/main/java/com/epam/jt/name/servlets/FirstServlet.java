package com.epam.jt.name.servlets;

import com.epam.jt.name.dao.DBManager;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBManager dbManager = DBManager.getInstance();
        try {
            List<User> users = dbManager.findAllUsers();
            List<Book> books = dbManager.findAllBooks();
            System.out.println("BOOKS" + books);

            HttpSession session = request.getSession();
            session.setAttribute("usrLst", users);
            session.setAttribute("bookList", books);

            request.getRequestDispatcher("/books.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       // User usr = dbManager.getStudentById(1);
        //request.setAttribute("st", st);
        //request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}