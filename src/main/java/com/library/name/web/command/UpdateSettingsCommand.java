package com.library.name.web.command;

import com.library.name.dao.Dao;
import com.library.name.dao.ReviewDao;
import com.library.name.dao.UserDao;
import com.library.name.entity.Review;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.List;

/**
 * Update settings items.
 */
public class UpdateSettingsCommand extends Command {

	private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);
	private static final Dao<User> userDao = UserDao.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Command starts");
		
		// UPDATE USER ////////////////////////////////////////////////////////
		
		User user = (User)request.getSession().getAttribute("user");
		boolean updateUser = false;
		
		// update first name
		String firstName = request.getParameter("firstName");
		if (firstName != null && !firstName.isEmpty()) {
			user.setFirstName(firstName);
			updateUser = true;
		}

		// update last name
		String lastName = request.getParameter("lastName");
		if (lastName != null && !lastName.isEmpty()) {
			user.setSecondName(lastName);
			updateUser = true;
		}

		String localeToSet = request.getParameter("localeToSet");
		if (localeToSet != null && !localeToSet.isEmpty()) {
			HttpSession session = request.getSession();
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);			
			session.setAttribute("defaultLocale", localeToSet);
			user.setUserLocale(localeToSet);
			updateUser = true;
		}
		
		if (updateUser)
			userDao.update(user);


		ReviewDao reviewDao = ReviewDao.getInstance();
		List<Review> userReviews = reviewDao.getAllByUserId(user.getId());
		request.setAttribute("userReviews", userReviews);
		
		log.debug("Command finished");
		response.sendRedirect("/library/controller?command=viewSettings");
		return null;
	}

}