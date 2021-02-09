package com.library.name.web.command;

import com.library.name.dao.BookDao;
import com.library.name.entity.Book;
import com.library.name.entity.User;

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
    private static String sortParam = "title";

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
        session.setAttribute("sortParameter", sortParam);
        Book book;
        List<Book> books;

        //search
        String filter = request.getParameter("filter");
        if (filter != null && !filter.isEmpty()) {
            System.out.println("request filter : " + filter);
            book = bookDao.getByTitle(filter);
            System.out.println("CATALOG FILTER COMMAND BOOK " + book);
            request.setAttribute("book", book);
            return "catalog.jsp";
        }


        String bookId = request.getParameter("bookid");
        if (bookId != null && !bookId.isEmpty()) {
            int id = Integer.parseInt(bookId);
            System.out.println(bookId + " bookId PARAM ");
            book = bookDao.get(id);
            request.setAttribute("book", book);
            return "aboutBook.jsp";
        }

        //search
        String page = request.getParameter("page");
        int pageid = 1;
        if (page != null && !page.isEmpty()) {
            pageid = Integer.parseInt(request.getParameter("page"));
        }
        try {
            String sortParamReq = request.getParameter("sort");
            if (sortParamReq != null && !sortParamReq.isEmpty()) {
                sortParam = sortParamReq;
            } else if (session.getAttribute("sortParameter") != null) {
                sortParam = (String) session.getAttribute("sortParameter");
            }

            books = bookDao.getSomeBooks(pageid, RECORDS_ON_PAGE, sortParam);
            request.setAttribute("listPagedBooks", books);

            System.out.println(books + "BOOKS");
            session.setAttribute("page", pageid);
            System.out.println(session.getAttribute("page"));

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                session.setAttribute("userId", user.getId());
            }
            return "/catalog.jsp";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "catalog.jsp";
    }

    /*private static void sortByParam(String sortParam, HttpSession session, List<Book> books) {
        switch (sortParam) {
            case "title":
                CompareByTitle compareByTitle = new CompareByTitle();
                session.setAttribute("sortParameter", sortParam);
                books.sort(compareByTitle);
                System.out.println("SORTED BY compareByTitle: " + books);
                break;
            case "author":
                CompareByAuthor compareByAuthor = new CompareByAuthor();
                books.sort(compareByAuthor);
                session.setAttribute("sortParameter", sortParam);
                System.out.println("SORTED BY AUTHOR: " + books);
                break;
        }
    }

    private static class CompareByAuthor implements Comparator<Book>, Serializable {
        public int compare(Book o1, Book o2) {
            if (o1.getAuthor().compareTo(o2.getAuthor()) > 0)
                return 1;
            if (o1.getAuthor().compareTo(o2.getAuthor()) < 0)
                return -1;
            if (o1.getAuthor().compareTo(o2.getAuthor()) == 0) {
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
            if (o1.getTitle().compareTo(o2.getTitle()) == 0) {
                if (o1.getNumber() - o2.getNumber() < 0)
                    return 1;
                if (o1.getNumber() - o2.getNumber() > 0)
                    return -1;
            }
            return 0;
        }
    }*/
}
