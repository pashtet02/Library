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

public class EditBookCommand extends Command {
    private static final Logger log = Logger.getLogger(EditBookCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse resp) throws IOException, ServletException {
        log.debug("Command starts");

        BookDao bookDao = BookDao.getInstance();
        Book book = new Book();

        //Send book on editBook.jsp page
        String bookId = request.getParameter("bookId");
        if (bookId != null && !bookId.isEmpty()) {
            long bookID = Long.parseLong(bookId);
            book = bookDao.get(bookID);
            request.setAttribute("book", book);
            return Path.PAGE_EDIT_BOOK;
        }

        //parameter from form to edit book
        boolean updateBook = false;
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            long ID = Long.parseLong(id);
            book = bookDao.get(ID);
        }

        String title = request.getParameter("title");
        if (title != null && !title.isEmpty()) {
            book.setTitle(title);
            updateBook = true;
        }

        String author = request.getParameter("author");
        if (author != null && !author.isEmpty()) {
            book.setAuthor(author);
            updateBook = true;
        }

        String ISBN = request.getParameter("ISBN");
        if (ISBN != null && !ISBN.isEmpty()) {
            book.setISBN(Long.parseLong(ISBN));
            updateBook = true;
        }

        String publisher = request.getParameter("publisher");
        if (publisher != null && !publisher.isEmpty()) {
            book.setPublisher(publisher);
            updateBook = true;
        }

        String publishingDate = request.getParameter("publishingDate");
        if (publishingDate != null && !publishingDate.isEmpty()) {
            book.setPublishingDate(Date.valueOf(publishingDate));
            updateBook = true;
        }

        String number = request.getParameter("number");
        if (number != null && !number.isEmpty()) {
            book.setNumber(Integer.parseInt(number));
            updateBook = true;
        }

        String descriptionUa = request.getParameter("descriptionUa");
        if (descriptionUa != null && !descriptionUa.isEmpty()) {
            book.setDescriptionUa(descriptionUa);
            updateBook = true;
        }

        String descriptionEn= request.getParameter("descriptionEn");
        if (descriptionEn != null && !descriptionEn.isEmpty()) {
            book.setDescriptionEn(descriptionEn);
            updateBook = true;
        }

        String language = request.getParameter("language");
        if (language != null && !language.isEmpty()) {
            book.setLanguage(language);
            updateBook = true;
        }

        if (updateBook){
            bookDao.update(book);
            log.debug("UPDATE BOOK: " + book);
        }

        log.debug("Command finished");
        String message = "You can change book photo: ";
        request.setAttribute("message", message);
        HttpSession session = request.getSession();
        session.setAttribute("bookTitle", book.getTitle());
        return Path.PAGE_ADD_IMAGE;
    }
}