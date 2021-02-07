package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.dao.OrderDao;
import com.library.name.dao.UserDao;
import com.library.name.entity.Book;
import com.library.name.entity.Order;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
        List<Order> ordersList = null;

        String status = request.getParameter("action");
        String orderId = request.getParameter("orderId");
        if (status != null && !status.isEmpty()){
            long id = Long.parseLong(orderId);
            orderDao.setOrderStatus(id, status);
            System.out.println("RETURN DATE:"+request.getParameter("returnDate"));
            //orderDao.setReturnDate(id, Date.valueOf("saf");
        }

        try {
            ordersList = orderDao.getAllReserved();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("ORDERS LIST:" + ordersList);

        for (Order order : ordersList) {
            Book book = bookDao.get(order.getBookId());
            User user = userDao.get(order.getUserId());
            order.setBookTitle(book.getTitle());
            order.setBookAuthor(book.getAuthor());
            order.setUserFine(user.getFine());
            order.setUserMail(user.getMail());
            order.setUsername(user.getUsername());
            order.setUserFirstName(user.getFirstName());
            order.setBooksNumber(book.getNumber());
            order.setComment("ДАйте мені будь ласка цю книжку! \n" +
                    "Я поверну через тиждень, мамой клянусь)))");
        }

        log.trace("Found in DB: ordersList --> " + ordersList);

        // put user order beans list to request
        request.setAttribute("ordersList", ordersList);
        log.trace("Set the request attribute: ordersList --> " + ordersList);

        log.debug("Commands finished");
        return Path.PAGE__LIBRARIAN_MENU_PAGE;
    }
}
