package com.library.name.web.command;

import com.library.name.dao.BookDao;
import com.library.name.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookCommand extends Command {
    /**
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     *                 Execution method for command.
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookDao bookDao = BookDao.getInstance();
        Book book;

        String bookId = request.getParameter("bookId");
        if (bookId != null && !bookId.isEmpty()) {
            long bookID = Long.parseLong(bookId);
            book = bookDao.get(bookID);
            bookDao.delete(book);
            response.sendRedirect("/library/controller?command=catalog&page=1");
        }
        return null;
    }
}
