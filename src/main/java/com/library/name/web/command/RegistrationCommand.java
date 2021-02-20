package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.library.name.service.Password.hashPassword;

public class RegistrationCommand extends Command {
    private final transient UserDao userDao = UserDao.getInstance();
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    /**
     * Execution method for command.
     *
     * @param req HttpServletRequest
     * @param response HttpServletResponse
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String login = req.getParameter(LOGIN);
        String email = req.getParameter("email");

        // error handler
        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;

        if (userDao.getUserByLogin(login).getUsername() != null) {
            errorMessage = "Such user already exists";
            req.setAttribute("userExistsError", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTRATION;
        }

        if (userDao.getUserByEmail(email).getMail() != null) {
            errorMessage = "User with such email already registered";
            req.setAttribute("emailExistsError", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return Path.PAGE_REGISTRATION;
        }

        User user = new User();
        user.setUsername(req.getParameter(LOGIN));

        //To hash passwords
        String pass1 = req.getParameter(PASSWORD);
        String pass2 = req.getParameter("password-repeat");

        if (!pass1.equals(pass2)) {
            String errMessage = "your passwords doesn`t match!!!";
            req.setAttribute("errMessage", errMessage);
            return Path.PAGE_REGISTRATION;
        }

        String computedHash = hashPassword(pass1);

        log.debug("COMPUTED HASH: " + computedHash);
        user.setPassword(computedHash);

        user.setFirstName(req.getParameter("firstName"));
        user.setSecondName(req.getParameter("secondName"));
        user.setMail(email);
        user.setFine(0.0);
        user.setRole("USER");
        user.setBanned(false);

        try {
            userDao.save(user);
            req.getSession().setAttribute(LOGIN, req.getParameter(LOGIN));
            req.getSession().setAttribute(PASSWORD, req.getParameter(PASSWORD));
            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("user", user);

            response.sendRedirect("/library/controller?command=catalog&page=1");
            return null;
        } catch (SQLException throwables) {
            log.error("REGISTRATION COMMAND: " + throwables.getMessage() + throwables.getSQLState());
            return forward;
        }
    }
}
