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

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger log = Logger.getLogger(EditUserCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = UserDao.getInstance();
        log.debug("Command starts");

        // UPDATE USER ////////////////////////////////////////////////////////
        if (request.getParameter("userId") != null && !request.getParameter("userId").isEmpty()){
            System.out.println("IF WORKS userId:" + request.getParameter("userId"));
            return Path.PAGE__EDIT_USER;
        }

        boolean updateUser = false;
        User user = new User();
        String userId = request.getParameter("id");
        if (userId != null && !userId.isEmpty()) {
            long userID = Long.parseLong(userId);
            user = userDao.get(userID);
        }

        String role = request.getParameter("role");
        if (role != null && !role.isEmpty()) {
            user.setRole(role.toUpperCase(Locale.ROOT));
            updateUser = true;
        }

        String isBanned = request.getParameter("isBanned");
        if (isBanned != null && !isBanned.isEmpty()) {
            System.out.println("SET USER BANNED: " + isBanned.equals("true"));
            user.setBanned(isBanned.equals("true"));
            System.out.println("USER: banned " + user.isBanned());
            updateUser = true;
        }

        if (updateUser){
            userDao.update(user);
        }

        log.debug("Command finished");
        String message = "User: " + user.getUsername() + " changed succesfully";
        request.setAttribute("message", message);
        return Path.PAGE__SUCCESS_PAGE;
    }
}
