package com.library.name.web.command;

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
        if (request.getParameter("userId") != null){
            System.out.println("IF WORKS ");
            return "edit_user.jsp";
        }

        boolean updateUser = false;
        User user = new User();

        String userId = request.getParameter("id");
        if (userId != null && !userId.isEmpty()) {
            long userID = Long.parseLong(userId);
            user = userDao.get(userID);
        }

        // update first name
        String fine = request.getParameter("fine");
        if (fine != null && !fine.isEmpty()) {
            user.setFine(Double.parseDouble(fine));
            updateUser = true;
        }

        // update last name
        String role = request.getParameter("role");
        if (role != null && !role.isEmpty()) {
            user.setRole(role.toUpperCase(Locale.ROOT));
            updateUser = true;
        }

        String isBanned = request.getParameter("isBanned");
        if (isBanned != null && !isBanned.isEmpty()) {
            user.setBanned(isBanned.equals("on"));
            System.out.println("USER: banned " + user.isBanned());
            updateUser = true;
        }

        if (updateUser)
            userDao.update(user);

        log.debug("Command finished");
        return "/library/catalog";
    }
}
