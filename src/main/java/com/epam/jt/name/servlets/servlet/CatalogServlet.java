package com.epam.jt.name.servlets.servlet;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

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
        request.setCharacterEncoding("UTF-8");
        Book book = null;
        List<Book> books;

        if (request.getParameter("bookid") != null && !request.getParameter("bookid").isEmpty()) {
            int id = Integer.parseInt(request.getParameter("bookid"));
            System.out.println(request.getParameter(request.getParameter("bookid")) + " BOOKID PARAM ");
            book = bookDao.get(id);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/aboutBook.jsp").forward(request, response);
        } else {
            try {
                int pageid = 1;
                if (request.getParameter("page") != null) {
                    pageid = Integer.parseInt(request.getParameter("page"));
                }
                int numOfRecordsOnPage = 6;
                books = bookDao.getSomeBooks(pageid, numOfRecordsOnPage);
                System.out.println(books + "BOOKS");
                request.setAttribute("listPagedBooks", books);
                session.setAttribute("page", pageid);
                System.out.println(session.getAttribute("page"));
                //books = bookDao.getAll();
                //FILTER ATTENTION it doesnt work with cyrilic letters

                if (request.getParameter("filter") != null && !request.getParameter("filter").isEmpty()) {
                    String str = request.getParameter("filter");
                    System.out.println("request filter : " + str);
                    book = bookDao.getByTitle(str);
                    System.out.println("SINGLE BOOK " + book);
                }

                if (request.getParameter("sort") != null && !request.getParameter("sort").isEmpty()) {
                    System.out.println(request.getParameter("sort") + " SORT PARAM ");

                    books = bookDao.getAllSortedBy(request.getParameter("sort"));
                    System.out.println("SORTED BOOKS"+ books);
                    request.setAttribute("listPagedBooks", books);
                }

                session.setAttribute("bookList", books);
                request.setAttribute("book", book);
                if (session.getAttribute("user") != null){
                    User user = (User) session.getAttribute("user");
                    session.setAttribute("userId", user.getId());
                }
                request.getRequestDispatcher("/catalog.jsp").forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
