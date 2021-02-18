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
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianMenuCommand extends Command {
    private static final Logger log = Logger.getLogger(LibrarianMenuCommand.class);
    private static final OrderDao orderDao = OrderDao.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     * Повинен вернути список всіх ордерів зі статусом RESERVED і повинні бути 3 кнопки "Видати до", "Відхилити", "Читальний зал"
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Commands starts");
        List<Order> ordersList = new ArrayList<>();

        String status = request.getParameter("action");
        String orderId = request.getParameter("orderId");
        if (status != null && !status.isEmpty()){
            long id = Long.parseLong(orderId);
            orderDao.setOrderStatus(id, status);

            //Only if we approve order book.number--
            if (status.equals("APPROVED")){
               boolean res = orderDao.approveOrder(id);
               if (!res){
                   request.setAttribute("err", "Something going wrong with approveOrder()");
                   return Path.PAGE_ERROR_PAGE;
               }
            }

            String date = request.getParameter("returnDate");
            if (date != null && !date.isEmpty()){
                orderDao.setReturnDate(id, Date.valueOf(date));
            }

            String librarianComment = request.getParameter("librarianComment");
            if (librarianComment != null && !librarianComment.isEmpty()){
                log.debug("LIBRARIAN COMMENT: " + librarianComment);
                orderDao.setLibrarianComment(id, librarianComment);
            }
        }

        try {
            ordersList = orderDao.getAllReserved();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log.debug("ORDERS LIST:" + ordersList);

        for (Order order : ordersList) {
            Book book = bookDao.get(order.getBookId());
            User user = userDao.get(order.getUserId());
            order.setBookTitle(book.getTitle());
            order.setBookAuthor(book.getAuthor());
            order.setUserFine(user.getFine());
            order.setUserMail(user.getMail());
            order.setUsername(user.getUsername());
            order.setUserFirstName(user.getFirstName());
            order.setUserSecondName(user.getSecondName());
            order.setBooksNumber(book.getNumber());
        }

        log.trace("Found in DB: ordersList --> " + ordersList);

        // put user order beans list to request
        request.setAttribute("ordersList", ordersList);
        log.trace("Set the request attribute: ordersList --> " + ordersList);

        log.debug("Commands finished");
        return Path.PAGE_LIBRARIAN_MENU_PAGE;
    }
}
