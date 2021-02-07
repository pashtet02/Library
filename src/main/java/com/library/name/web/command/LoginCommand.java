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

import static com.library.name.service.Password.checkPassword;

public class LoginCommand extends Command {

    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        log.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");

        // error handler
        String errorMessage;
        String forward = Path.PAGE__ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }
        UserDao userDao = UserDao.getInstance();
        User user = userDao.getUserByLogin(login);
        log.trace("Found in DB: user --> " + user);
        System.out.println("LOGIN COMAND USER: " + user);

        //boolean bool = checkPassword(password, user.getPassword()); tam !bool
        if (user.getUsername() == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            //System.out.println("LOGIN COMMAND BOOL: " + bool);
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            log.trace("userRole --> " + user.getRole());

            if (user.getRole().equals("ADMIN"))
                forward = "admin_menu.jsp";

            if (user.getRole().equals("LIBRARIAN"))
                forward = "admin_menu.jsp";

            if (user.getRole().equals("USER"))
                forward = "user_menu.jsp";

            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", user.getRole());
            log.trace("Set the session attribute: userRole --> " + user.getRole());

            log.info("User " + user + " logged as " + user.getRole().toLowerCase());
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("user", user);
            System.out.println("IS BANNED " + user.isBanned());
            request.getSession().setAttribute("isBanned", Boolean.toString(user.isBanned()));

            // work with i18n
            String userLocaleName = user.getUserLocale();
            log.trace("userLocalName --> " + userLocaleName);

            if (userLocaleName != null && !userLocaleName.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

                session.setAttribute("defaultLocale", userLocaleName);
                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
                System.out.println("LOCALE LOGIN COMMAND: " + userLocaleName);
                log.info("Locale for user: defaultLocale --> " + userLocaleName);
            }
        }

        log.debug("Command finished");
        return forward;
    }

}