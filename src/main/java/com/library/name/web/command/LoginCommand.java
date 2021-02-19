package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.OrderDao;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

import static com.library.name.service.Password.checkPassword;

public class LoginCommand extends Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);
    private static final String errMessage = "errorMessage";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: logging --> " + login);

        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute(errMessage, errorMessage);
            log.error(errMessage + errorMessage);
            return forward;
        }

        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserByLogin(login);

        log.trace("Found in DB: user --> " + user);
        log.debug("LOGIN COMMAND USER: " + user.getUsername());

        boolean bool = checkPassword(password, user.getPassword());
        if (user.getUsername() == null || !bool) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error(errMessage + errorMessage);
            return forward;
        }

        if (user.isBanned()){
            errorMessage = "Sorry you were banned on this website :(";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        log.trace("userRole --> " + user.getRole());

        if (user.getRole().equals("ADMIN"))
            forward = "/controller?command=usersList";

        if (user.getRole().equals("LIBRARIAN"))
            forward = "/controller?command=librarianMenu";

        if (user.getRole().equals("USER"))
            forward = "/controller?command=listOrders";

        session.setAttribute("user", user);
        log.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", user.getRole());
        log.trace("Set the session attribute: userRole --> " + user.getRole());

        log.info("User " + user + " logged as " + user.getRole().toLowerCase());
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("role", user.getRole());
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("filterParam", "title");
        log.debug("IS BANNED " + user.isBanned());
        request.getSession().setAttribute("isBanned", Boolean.toString(user.isBanned()));

        // work with i18n
        String userLocaleName = user.getUserLocale();
        log.trace("userLocalName --> " + userLocaleName);

        if (userLocaleName != null && !userLocaleName.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

            session.setAttribute("defaultLocale", userLocaleName);
            log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
            log.debug("LOCALE LOGIN COMMAND: " + userLocaleName);
            log.info("Locale for user: defaultLocale --> " + userLocaleName);
        }

        log.info("user old fine = " + user.getFine());
        OrderDao orderDao = OrderDao.getInstance();
        user.setFine(orderDao.countUserFineByUserId(user.getId()));
        userDao.update(user);
        log.info("user new fine = " + user.getFine());

        log.debug("Command finished");
        return forward;
    }
}