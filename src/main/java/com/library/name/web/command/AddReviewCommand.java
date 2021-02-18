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

public class AddReviewCommand extends Command {

    private static final Logger log = Logger.getLogger(AddReviewCommand.class);

    @Override
    public String execute(HttpServletRequest req,
                          HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();

        log.debug("Command starts");

        if (req.getParameter("userComment") == null) {
            BookDao bookDao = BookDao.getInstance();
            long bookId = Long.parseLong(req.getParameter("bookId"));
            Book book = bookDao.get(bookId);
            req.setAttribute("book", book);
            return Path.PAGE_ADD_REVIEW;
        }

        ReviewDao reviewDao = ReviewDao.getInstance();

        Review review = new Review();
        User user = (User) session.getAttribute("user");
        review.setUserId(user.getId());
        review.setBookId(Long.parseLong(req.getParameter("bookId")));
        review.setMark(Integer.parseInt(req.getParameter("mark")));
        review.setUserComment(req.getParameter("userComment"));

        try {
            reviewDao.save(review);
        } catch (SQLException throwables) {
            String errorMessage = throwables.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            log.error("Set the request attribute: errorMessage --> " + errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        String message;
        message = "Review added!!!";
        req.setAttribute("massage", message);
        req.setAttribute("commandName", "catalog");
        log.debug("Command finished");

        return "/controller?command=catalog&bookid=" + review.getBookId();
    }
}