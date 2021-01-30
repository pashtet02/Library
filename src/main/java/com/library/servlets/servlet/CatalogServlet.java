package com.library.servlets.servlet;

import com.library.dao.BookDao;
import com.library.domain.Book;

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
        Book book = null;
        List<Book> books = null;
        try {
            int pageid = 1;
            if (request.getParameter("page") != null) {
                pageid = Integer.parseInt(request.getParameter("page"));
            }
            int numOfRecordsOnPage = 3;
            //List<Book> list;
            books = bookDao.getSomeBooks(pageid, numOfRecordsOnPage);
            System.out.println(books.size() + "page   " + pageid);
            request.setAttribute("listPagedBooks", books);
            session.setAttribute("page", pageid);
            System.out.println(session.getAttribute("page"));
            books = bookDao.getAll();
            //FILTER ATTENTION it doesnt work with cyrilic letters

            if (request.getParameter("filter") != null && !request.getParameter("filter").isEmpty()) {
                String str = request.getParameter("filter");
                System.out.println("request filter : " + str);
                book = bookDao.getByTitle(str);
                System.out.println("SINGLE BOOK " + book);
            }

            /*if (request.getParameter("sort") != null && !request.getParameter("sort").isEmpty()){
                System.out.println(request.getParameter(request.getParameter("sort"))+ " SORT PARAM ");
                books = bookDao.getAllSortedBy(request.getParameter("sort"));
            }*/

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
