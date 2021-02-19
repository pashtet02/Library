package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.ReviewDao;
import com.library.name.entity.Review;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * View settings command.
 */
public class ViewSettingsCommand extends Command {
	private static final Logger log = Logger.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {		
		log.debug("Command starts");
		ReviewDao reviewDao = ReviewDao.getInstance();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Review> userReviews = reviewDao.getAllByUserId(user.getId());
		request.setAttribute("userReviews", userReviews);
		
		log.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}