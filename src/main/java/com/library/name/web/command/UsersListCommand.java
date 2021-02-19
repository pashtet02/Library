package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * For librarian returns readers.jsp page with users with role 'USER'
 * For admin returns users.jsp page with users with ALL roles
 * For everyone else return error_page.jsp
 * */
public class UsersListCommand extends Command{
    private static final Logger log = Logger.getLogger(UsersListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = UserDao.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        List<User> users;
        try {
            if (role != null && role.equals("ADMIN")){
                users = userDao.getAll();
                request.setAttribute("usersList", users);
                return Path.PAGE_USERS_LIST;
            }
            if (role != null && role.equals("LIBRARIAN")){
                users = userDao.getAllUsers();
                request.setAttribute("readersList", users);
                return Path.PAGE_READERS;
            }
        } catch (SQLException throwables) {
            log.error(throwables.getMessage() + throwables.getSQLState());
        }

        return Path.PAGE_ERROR_PAGE;
    }
}
