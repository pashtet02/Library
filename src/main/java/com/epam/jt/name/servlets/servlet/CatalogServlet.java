package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.domain.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CatalogServlet extends HttpServlet {
    private HttpSession session;
    private final BookDao bookDao = BookDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Book book = null;
        try {
            //FILTER ATTENTION it doesnt work with cyrilic letters
            System.out.println(request.getCharacterEncoding());
            List<Book> books = null;

            if (request.getParameter("filter") != null && !request.getParameter("filter").isEmpty()) {
                String str = request.getParameter("filter");
                System.out.println("request filter : " + str);
                book = bookDao.getByTitle(str);
                System.out.println("SINGLE BOOK " + book);
            }else {
                int pageid = Integer.parseInt(request.getParameter("page"));
                int total = 2;
                if (pageid != 1) {
                    pageid = pageid - 1;
                    pageid = pageid * total + 1;
                }
                List<Book> list = null;
                try {
                    list = bookDao.getSomeBooks(pageid, total);
                    request.setAttribute("listPagedBooks", list);
                    session.setAttribute("page", pageid);
                    System.out.println(session.getAttribute("page"));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                books = bookDao.getAll();
            }

            if (request.getParameter("sort") != null){
                books = bookDao.getAllSortedBy(request.getParameter("sort"));
            }

            session.setAttribute("bookList", books);
            request.setAttribute("book", book);

            request.getRequestDispatcher("/catalog.jsp").forward(request, response);
            //response.sendRedirect("/catalog");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
