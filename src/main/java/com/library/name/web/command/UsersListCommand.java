package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.UserDao;
import com.library.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsersListCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> users = userDao.getAll();

            request.getSession().setAttribute("usersList", users);

            return "users.jsp";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Path.PAGE__ERROR_PAGE;
    }
}
