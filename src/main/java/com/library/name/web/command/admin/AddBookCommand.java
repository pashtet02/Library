package com.library.name.web.command.admin;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.entity.Book;
import com.library.name.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AddBookCommand extends Command {

    private static final Logger log = Logger.getLogger(AddBookCommand.class);

    @Override
    public String execute(HttpServletRequest req,
                          HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        log.debug("Command starts");
        String errorMessage;
        String title = req.getParameter("title");
        if (title == null || title.isEmpty()) {
            return Path.PAGE_ADD_BOOK;
        }

        //Подумати про білдер
        BookDao bookDao = BookDao.getInstance();
        if (bookDao.getByTitle(title).getTitle() != null) {
            errorMessage = "Such book already exists";
            req.setAttribute("titleExistsError", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_ADD_BOOK;
        }

        String isbn = req.getParameter("ISBN");
        if (bookDao.getByISBN(Long.parseLong(isbn)).getISBN() > 0) {
            errorMessage = "Book with such ISBN already exists";
            req.setAttribute("isbnExistsError", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_ADD_BOOK;
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(req.getParameter("author"));
        book.setISBN(Long.parseLong(isbn));
        book.setPublisher(req.getParameter("publisher"));

        Date date = Date.valueOf(req.getParameter("publishingDate"));
        book.setPublishingDate(date);

        String language = req.getParameter("language");
        book.setLanguage(language);
        book.setNumber(Integer.parseInt(req.getParameter("number")));

        if (language.equals("ukrainian")){
            book.setDescriptionUa(req.getParameter("description"));
        } else book.setDescriptionEn(req.getParameter("description"));

        try {
            bookDao.save(book);
        } catch (SQLException throwables) {
             errorMessage = throwables.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            log.error("Set the request attribute: errorMessage --> " + errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        log.debug("Command finished");
        String message = "Book " + book.getTitle() + "added!!!";
        req.setAttribute("massage", message);
        HttpSession session = req.getSession();
        session.setAttribute("bookTitle", book.getTitle());
        return Path.PAGE_ADD_IMAGE;
    }
}