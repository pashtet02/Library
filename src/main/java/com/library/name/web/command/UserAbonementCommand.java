package com.library.name.web.command;

import com.library.name.dao.BookDao;
import com.library.name.dao.OrderDao;
import com.library.name.entity.Book;
import com.library.name.entity.Order;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserAbonementCommand extends Command{
    private static final Logger log = Logger.getLogger(UserAbonementCommand.class);
    private static final OrderDao orderDao = OrderDao.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     * Повинен відповідати за кабінет користувача
     * -Список книг на абонементі, бажано зробития якесь розмежування типу В мене, очікують розгляду, історія, прострочені
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Commands starts");
        List<Order> ordersList;

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        ordersList = orderDao.getByUserId(user.getId());
        System.out.println("USER: in list orders" + user);
        System.out.println("ORDERS LIST:" + ordersList);

        for (Order order : ordersList) {
            Book book = bookDao.get(order.getBookId());
            order.setBookTitle(book.getTitle());
            order.setBookAuthor(book.getAuthor());
        }

        log.trace("Found in DB: ordersList --> " + ordersList);

        // put user order beans list to request
        request.setAttribute("ordersList", ordersList);
        log.trace("Set the request attribute: ordersList --> " + ordersList);

        log.debug("Commands finished");
        return "books.jsp";
    }
}
