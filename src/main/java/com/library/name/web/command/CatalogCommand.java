package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.dao.ReviewDao;
import com.library.name.entity.Book;
import com.library.name.entity.Review;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CatalogCommand extends Command {

    private final BookDao bookDao = BookDao.getInstance();
    private final ReviewDao reviewDao = ReviewDao.getInstance();
    private static final int RECORDS_ON_PAGE = 6;
    private String sortParam = "title";
    private static final Logger log = Logger.getLogger(CatalogCommand.class);


    /**
     * Execution method for command.
     *
     * @param request HttpServletRequest request
     *                Request params:
     *                filterParam to search books by its title or author
     *                bookid = return about.jsp page with book info
     *                page = return boos from DB on selected page
     *                sortParam = sorting books by parameter(title, author, publisher, publishingDate)
     * @param response HttpServletResponse response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("sortParameter", sortParam);
        Book book;
        List<Book> books;

        //search by parameter
        String filterParam = request.getParameter("filterParam");
        String filter = request.getParameter("filter");

        if (filterParam != null && !filterParam.isEmpty()) {
            log.debug("request filterParam : " + filterParam);
            if (filterParam.equals("author")){
                System.out.println("FILTER: " + filterParam + filter);
                books = bookDao.getAllByAuthor(filter);
                request.setAttribute("listPagedBooks", books);
                session.setAttribute("filterParam", filterParam);
            }
            else {
                book = bookDao.getByTitle(filter);
                session.setAttribute("filterParam", filterParam);
                request.setAttribute("book", book);
            }
            return Path.PAGE_CATALOG;
        }

        //About book page
        String bookId = request.getParameter("bookid");
        if (bookId != null && !bookId.isEmpty()) {
            int id = Integer.parseInt(bookId);
            log.debug(bookId + " bookId PARAM ");
            book = bookDao.get(id);
            List<Review> bookReviews = reviewDao.getAllByBookId(book.getId());
            log.debug("BOOK CATALOG DETAILS: " + book);
            request.setAttribute("bookReviews", bookReviews);
            request.setAttribute("book", book);
            return Path.PAGE_ABOUT_BOOK;
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

            //log.debug(books + "BOOKS");
            session.setAttribute("page", pageid);

            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                session.setAttribute("userId", user.getId());
            }
            return Path.PAGE_CATALOG;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Path.PAGE_CATALOG;
    }
}
