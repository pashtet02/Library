package com.epam.jt.name.servlets;

import com.epam.jt.name.dao.OrderDao;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.Order;
import com.epam.jt.name.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class BookOrderServlet extends HttpServlet {
    private final OrderDao orderDao = OrderDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/orderBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //User user =  (User)req.getSession().getAttribute("user");
        Long userId = Long.parseLong(req.getParameter("userId"));

        Order order = new Order();
        order.setUserId(userId);
        order.setBookId(Long.parseLong(req.getParameter("bookId")));

        order.setReturnDate(Date.valueOf(req.getParameter("returnDate")));
        order.setStatus("RESERVED");
        try {
            System.out.println(order);
            orderDao.save(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.sendRedirect("/library/catalog");
    }

}
