package com.library.name.web.command.user;

import com.library.name.Path;
import com.library.name.dao.OrderDao;
import com.library.name.entity.Order;
import com.library.name.entity.User;
import com.library.name.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderBookCommand extends Command {
    private final OrderDao orderDao = OrderDao.getInstance();
    private static final Logger log = Logger.getLogger(OrderBookCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        long userId = Long.parseLong(req.getParameter("userId"));
        OrderDao orderDao = OrderDao.getInstance();

        if (user.getFine() > 0){
            String err = "You have to pay fine before order a new book";
            req.setAttribute("errorMessage", err);
            return Path.PAGE_ERROR_PAGE;
        }

        List<Order> orders = orderDao.getByUserId(user.getId());
        long bookId = Long.parseLong(req.getParameter("bookId"));
        boolean userHasBook = false;
        for (Order order: orders) {
            if (order.getBookId() == bookId && !order.getStatus().equals("RETURNED")) {
                userHasBook = true;
                break;
            }
        }
        if (userHasBook){
            String err = "You have ordered this book already";
            req.setAttribute("errorMessage", err);
            return Path.PAGE_ERROR_PAGE;
        }

        String comment = req.getParameter("comment");
        if (comment != null && !comment.isEmpty()) {
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

                log.debug("COMMENT: " + req.getParameter("comment"));
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

                response.sendRedirect("/library/controller?command=listOrders");
                return null;
            } else {
                String err = "You have ordered this book already";
                req.setAttribute("errorMessage", err);
                return Path.PAGE_ERROR_PAGE;
            }
        }
        return Path.PAGE_ORDER_BOOK;
    }
}
