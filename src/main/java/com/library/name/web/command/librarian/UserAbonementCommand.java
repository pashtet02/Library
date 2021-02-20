package com.library.name.web.command.librarian;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.dao.OrderDao;
import com.library.name.dao.UserDao;
import com.library.name.entity.Book;
import com.library.name.entity.Order;
import com.library.name.entity.User;
import com.library.name.web.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class UserAbonementCommand extends Command {
    private static final Logger log = Logger.getLogger(UserAbonementCommand.class);
    private static final OrderDao orderDao = OrderDao.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    /**
     * Execution method for command.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return userAbonement.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Commands starts");
        List<Order> ordersList;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long id;
        User usr = null;

        String action = request.getParameter("action");
        if (action != null && action.equals("return")) {
            String orderId = request.getParameter("orderId");
            long ordId = Long.parseLong(orderId);
            Order order = orderDao.get(ordId);
            System.out.println("ORDER" + order);

            long bookId = order.getBookId();
            bookDao.incrementNumberBook(bookId);
            orderDao.setOrderStatus(ordId, "RETURNED");
            request.setAttribute("message", "Book returned succesfully");

            response.sendRedirect("/library/controller?command=usersList");
            return null;
        }

        String userId = request.getParameter("userId");
        if (userId != null && !userId.isEmpty()) {
            id = Long.parseLong(userId);
            usr = userDao.get(id);
        } else {
            id = user.getId();
        }

        ordersList = orderDao.getByUserId(id);
        log.debug("ABONEMENT order list of user" + ordersList);

        for (Order order : ordersList) {
            Book book = bookDao.get(order.getBookId());
            order.setBookTitle(book.getTitle());
            order.setBookAuthor(book.getAuthor());
        }

        Comparator<Order> comparator = Comparator.comparing(Order::getStatus);
        ordersList.sort(comparator);
        log.trace("Found in DB: ordersList --> " + ordersList);

        // put user order beans list to request

        request.setAttribute("usr", usr);
        request.setAttribute("ordersList", ordersList);
        log.trace("Set the request attribute: ordersList --> " + ordersList);

        log.debug("Commands finished");
        return Path.PAGE_USER_ABONEMENT;
    }
}
