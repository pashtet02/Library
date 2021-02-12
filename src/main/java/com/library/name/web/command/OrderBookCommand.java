package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.OrderDao;
import com.library.name.entity.Order;
import com.library.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Long userId = Long.parseLong(req.getParameter("userId"));

        String comment = req.getParameter("comment");
        if (comment != null && !comment.isEmpty()) {
            Order order = new Order();
            order.setUserId(userId);
            order.setBookId(Long.parseLong(req.getParameter("bookId")));

            String firstName = req.getParameter("firstName");
            if (firstName == null || firstName.isEmpty()) {
                firstName = user.getFirstName();
            }
            order.setUserFirstName(firstName);

            String secondName = req.getParameter("secondName");
            if (secondName == null || secondName.isEmpty()) {
                secondName = user.getSecondName();
            }
            order.setUserSecondName(secondName);

            String email = req.getParameter("email");
            if (email == null || email.isEmpty()) {
                email = user.getMail();
            }
            order.setUserMail(email);

            System.out.println("COMMENT: " + req.getParameter("comment"));
            order.setUserComment(req.getParameter("comment"));

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
            req.setAttribute("commandName", "catalog");
            return Path.PAGE__SUCCESS_PAGE;
        }

        return Path.PAGE__ORDER_BOOK;
    }
}

