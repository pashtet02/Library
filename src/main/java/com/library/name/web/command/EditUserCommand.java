package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class EditUserCommand extends Command {
    private static final Logger log = Logger.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = UserDao.getInstance();
        log.debug("Command starts");

        // UPDATE USER ////////////////////////////////////////////////////////
        String userId = request.getParameter("userId");
        if ( userId != null && !userId.isEmpty()){
            log.debug("IF WORKS userId:" + userId);
            return Path.PAGE_EDIT_USER;
        }

        boolean updateUser = false;
        User user = new User();
        String userID = request.getParameter("id");
        if (userID != null && !userID.isEmpty()) {
            long id = Long.parseLong(userID);
            user = userDao.get(id);
        }

        String role = request.getParameter("role");
        if (role != null && !role.isEmpty()) {
            user.setRole(role.toUpperCase(Locale.ROOT));
            updateUser = true;
        }

        String isBanned = request.getParameter("isBanned");
        if (isBanned != null && !isBanned.isEmpty()) {
           log.debug("SET USER BANNED: " + isBanned.equals("true"));
            user.setBanned(isBanned.equals("true"));
            log.debug("USER: banned " + user.isBanned());
            updateUser = true;
        }

        if (updateUser){
            userDao.update(user);
        }

        log.debug("Command finished");
        String message = "User: " + user.getUsername() + " changed succesfully";
        request.setAttribute("message", message);
        return Path.PAGE_SUCCESS_PAGE;
    }
}
