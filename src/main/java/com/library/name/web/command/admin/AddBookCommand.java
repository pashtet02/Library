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

        if (req.getParameter("title") == null) {
            return Path.PAGE_ADD_BOOK;
        }

        //Подумати про білдер
        log.debug("ADD BOOK COMMAND ");
        BookDao bookDao = BookDao.getInstance();

        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setISBN(Long.parseLong(req.getParameter("ISBN")));
        book.setPublisher(req.getParameter("publisher"));

        Date date = Date.valueOf(req.getParameter("publishingDate"));
        book.setPublishingDate(date);

        String language = req.getParameter("language");
        System.out.println("Language: " + language);
        book.setLanguage(language);
        book.setNumber(Integer.parseInt(req.getParameter("number")));

        System.out.println("DESCRIPTION: " + req.getParameter("description"));
        if (language.equals("ukrainian")){
            book.setDescriptionUa(req.getParameter("description"));
        } else book.setDescriptionEn(req.getParameter("description"));

        try {
            bookDao.save(book);
        } catch (SQLException throwables) {
            String errorMessage = throwables.getMessage();
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