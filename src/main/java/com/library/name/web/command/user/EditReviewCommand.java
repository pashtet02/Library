package com.library.name.web.command.user;

import com.library.name.Path;
import com.library.name.dao.ReviewDao;
import com.library.name.entity.Review;
import com.library.name.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditReviewCommand extends Command {
    private static final Logger log = Logger.getLogger(EditReviewCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        ReviewDao reviewDao = ReviewDao.getInstance();
        log.debug("Command starts");
        Review review = new Review();


        // UPDATE review ////////////////////////////////////////////////////////
        String revId = request.getParameter("reviewId");
        if ( revId != null && !revId.isEmpty()){
            long revIdLong = Long.parseLong(revId);
            review = reviewDao.get(revIdLong);
            request.setAttribute("review", review);
            log.debug("IF WORKS userId:" + revId);
            return Path.PAGE_EDIT_REVIEW;
        }

        boolean updateReview = false;
        String reviewID = request.getParameter("id");
        if (reviewID != null && !reviewID.isEmpty()) {
            long id = Long.parseLong(reviewID);
            review = reviewDao.get(id);
        }

        String userComment = request.getParameter("userComment");
        if (userComment != null && !userComment.isEmpty()) {
            review.setUserComment(userComment);
            updateReview = true;
        }

        String mark = request.getParameter("mark");
        if (mark != null && !mark.isEmpty()) {
            review.setMark(Integer.parseInt(mark));
            log.debug("Review: mark " + review.getMark());
            updateReview = true;
        }

        if (updateReview){
            reviewDao.update(review);
        }

        log.debug("Command finished");
        return "/controller?command=viewSettings";
    }
}
