package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.domain.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static BookDao bookDao = BookDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}