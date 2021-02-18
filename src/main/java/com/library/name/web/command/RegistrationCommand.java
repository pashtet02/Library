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
    private final UserDao userDao = UserDao.getInstance();
    private static final Logger log = Logger.getLogger(RegistrationCommand.class);
    private static String LOGIN = "login";
    private static String password = "password";

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
        User userTest = userDao.getUserByLogin(login);
        // error handler
        String errorMessage;
        String forward = Path.PAGE_ERROR_PAGE;
        if (userTest.getUsername() != null) {
            errorMessage = "Such user already exists";
            req.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = new User();
        user.setUsername(req.getParameter(LOGIN));

        //To hash passwords
        String pass1 = req.getParameter(password);
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
        user.setMail(req.getParameter("email"));
        user.setFine(0.0);
        user.setRole("USER");
        user.setBanned(false);


        try {
            userDao.save(user);
            req.getSession().setAttribute("login", req.getParameter(LOGIN));
            req.getSession().setAttribute("password", req.getParameter(password));
            req.getSession().setAttribute("role", user.getRole());
            req.getSession().setAttribute("user", user);
            return "/controller?command=catalog&page=1";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            log.error("REGISTRATION COMMAND: " + throwables.getMessage() + throwables.getSQLState());
            return forward;
        }
    }
}
