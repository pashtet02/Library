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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderBookCommand extends Command {
    private final OrderDao orderDao = OrderDao.getInstance();

    /**
     * Execution method for command.
     *
     * @param req f
     * @param response f
     */

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        long userId = Long.parseLong(req.getParameter("userId"));

        if (user.getFine() > 0){
            String err = "You have to pay fine before order a new book";
            req.setAttribute("errorMessage", err);
            return Path.PAGE_ERROR_PAGE;
        }

        String comment = req.getParameter("comment");
        if (comment != null && !comment.isEmpty()) {
            long bookId = Long.parseLong(req.getParameter("bookId"));
            if (!orderDao.isOrderExists(userId, bookId)) {

                Order order = new Order();
                order.setUserId(userId);
                order.setBookId(bookId);

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
                    return Path.PAGE_ERROR_PAGE;
                }
                req.setAttribute("commandName", "catalog");
                return "/controller?command=listOrders";
            } else {
                String err = "You have ordered this book already";
                req.setAttribute("errorMessage", err);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        return Path.PAGE_ORDER_BOOK;
    }
}
