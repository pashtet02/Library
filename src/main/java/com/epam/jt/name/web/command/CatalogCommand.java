package com.epam.jt.name.web.command;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class CatalogCommand extends Command {
    private HttpSession session;
    private final BookDao bookDao = BookDao.getInstance();
    private static final int RECORDS_ON_PAGE = 6;

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        session = request.getSession();
        Book book;
        List<Book> books;
        //TODO FILTER ATTENTION it doesnt work with cyrilic letters FIX
        //search
        String filter = request.getParameter("filter");
        if (filter != null && !filter.isEmpty()) {
            System.out.println("request filter : " + filter);
            book = bookDao.getByTitle(filter);
            System.out.println("CATALOG FILTER COMMAND BOOK " + book);
            request.setAttribute("book", book);
            return "catalog.jsp";
        }


        String bookId = request.getParameter("bookId");
        if (bookId != null && !bookId.isEmpty()) {
            int id = Integer.parseInt(bookId);
            System.out.println(bookId + " bookId PARAM ");
            book = bookDao.get(id);
            request.setAttribute("book", book);
            return "aboutBook.jsp";
        }
        //TODO SORT AND PAGINATION DONT WORK TOGETHER
        //search
        String page = request.getParameter("page");
        int pageid = 1;
        if (page != null && !page.isEmpty()) {
            pageid = Integer.parseInt(request.getParameter("page"));
        }
            try {

                books = bookDao.getSomeBooks(pageid, RECORDS_ON_PAGE);

                String sortParam = request.getParameter("sort");
                if (sortParam != null && !sortParam.isEmpty()) {
                    switch (sortParam){
                        case "title":
                            CompareByTitle compareByTitle = new CompareByTitle();
                            books.sort(compareByTitle);
                            System.out.println("SORTED BY compareByTitle: " + books);
                            break;
                        case "author":
                            CompareByAuthor compareByAuthor = new CompareByAuthor();
                            books.sort(compareByAuthor);
                            System.out.println("SORTED BY AUTHOR: " + books);
                            break;
                        case "publisher":
                            break;
                        case "publishingDate":
                            break;
                    }
                }
                request.setAttribute("listPagedBooks", books);

                System.out.println(books + "BOOKS");
                session.setAttribute("page", pageid);
                System.out.println(session.getAttribute("page"));

                if (session.getAttribute("user") != null){
                    User user = (User) session.getAttribute("user");
                    session.setAttribute("userId", user.getId());
                }
                return "/catalog.jsp";
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        return "catalog.jsp";
    }
    private static class CompareByAuthor implements Comparator<Book>, Serializable {
        public int compare(Book o1, Book o2) {
            if (o1.getAuthor().compareTo(o2.getAuthor()) > 0)
                return 1;
            if (o1.getAuthor().compareTo(o2.getAuthor()) < 0)
                return -1;
            if (o1.getAuthor().compareTo(o2.getAuthor()) == 0){
                if (o1.getNumber() - o2.getNumber() < 0)
                    return 1;
                if (o1.getNumber() - o2.getNumber() > 0)
                    return -1;
            }
            return 0;
        }
    }
    private static class CompareByTitle implements Comparator<Book>, Serializable {
        public int compare(Book o1, Book o2) {
            if (o1.getTitle().compareTo(o2.getTitle()) > 0)
                return 1;
            if (o1.getTitle().compareTo(o2.getTitle()) < 0)
                return -1;
            if (o1.getTitle().compareTo(o2.getTitle()) == 0){
                if (o1.getNumber() - o2.getNumber() < 0)
                    return 1;
                if (o1.getNumber() - o2.getNumber() > 0)
                    return -1;
            }
            return 0;
        }
    }
}
