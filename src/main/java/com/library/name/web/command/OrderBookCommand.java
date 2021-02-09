package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.OrderDao;
import com.library.name.entity.Order;
import com.library.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class OrderBookCommand extends Command {
    private final OrderDao orderDao = OrderDao.getInstance();

    /**
     * Execution method for command.
     *
     * @param req
     * @param response
     * @return Address to go once the command is executed.
     */


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        Long userId = Long.parseLong(req.getParameter("userId"));



        String firstName = req.getParameter("firstName");
        if (firstName != null && !firstName.isEmpty()){
            Order order = new Order();
            order.setUserId(userId);
            order.setBookId(Long.parseLong(req.getParameter("bookId")));
            order.setUserFirstName(firstName);
            order.setUserMail(req.getParameter("email"));
            order.setUserSecondName(req.getParameter("secondName"));
            if (req.getParameter("comment") != null && !req.getParameter("comment").isEmpty()){
                System.out.println("COMMENT: " + req.getParameter("comment"));
                order.setUserComment(req.getParameter("comment"));
            }
            order.setStatus("RESERVED");
            try {
                System.out.println(order);
                orderDao.save(order);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                String errorMessage = throwables.getMessage();
                req.setAttribute("errorMessage", errorMessage);
                return Path.PAGE__ERROR_PAGE;
            }
            return Path.PAGE__SUCCESS_PAGE;
        }
        return "orderBook.jsp";


    }
}

