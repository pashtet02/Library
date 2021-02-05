package com.epam.jt.name.web.command;

import com.epam.jt.name.Path;
import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.entity.Book;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddBookCommand extends Command {

    private static final Logger log = Logger.getLogger(AddBookCommand.class);

    @Override
    public String execute(HttpServletRequest req,
                          HttpServletResponse resp) throws IOException, ServletException {

        log.debug("Command starts");

        if (req.getParameter("title") == null){
            return "addBook.jsp";
        }
        else {
            BookDao bookDao = BookDao.getInstance();

            Book book = new Book();
            book.setTitle(req.getParameter("title"));
            book.setAuthor(req.getParameter("author"));
            book.setISBN(Long.parseLong(req.getParameter("ISBN")));
            book.setPublisher(req.getParameter("publisher"));
            book.setNumber(Integer.parseInt(req.getParameter("number")));
            System.out.println("Command Add book: " +  book);
            try {
                bookDao.save(book);
            } catch (SQLException throwables) {
                String errorMessage = throwables.getMessage();
                req.setAttribute("errorMessage", errorMessage);
                log.error("Set the request attribute: errorMessage --> " + errorMessage);
                return Path.PAGE__ERROR_PAGE;
            }

            log.debug("Command finished");
            return "/library/first";
        }
    }
}