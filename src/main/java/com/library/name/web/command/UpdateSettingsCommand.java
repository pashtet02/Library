package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * Update settings items.
 * 
 *
 */
public class UpdateSettingsCommand extends Command {

	private static final Logger log = Logger.getLogger(UpdateSettingsCommand.class);
	private static UserDao userDao = UserDao.getInstance();

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

		
		log.debug("Command finished");
		return Path.PAGE__SETTINGS;
	}

}