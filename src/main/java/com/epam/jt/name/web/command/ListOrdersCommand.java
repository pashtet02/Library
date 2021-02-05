package com.epam.jt.name.web.command;

import com.epam.jt.name.dao.BookDao;
import com.epam.jt.name.dao.OrderDao;
import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.entity.Book;
import com.epam.jt.name.entity.Order;
import com.epam.jt.name.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListOrdersCommand extends Command {

    private static final long serialVersionUID = 1863978254689586513L;

    private static final Logger log = Logger.getLogger(ListOrdersCommand.class);
    private static final OrderDao orderDao = OrderDao.getInstance();
    private static final UserDao userDao = UserDao.getInstance();
    private static final BookDao bookDao = BookDao.getInstance();
    /**
     * Serializable comparator used with TreeMap container. When the servlet
     * container tries to serialize the session it may fail because the session
     * can contain TreeMap object with not serializable comparator.
     *
     * @author D.Kolesnikov
     *
     */
    /*private static class CompareById implements Comparator<Order>, Serializable {
        private static final long serialVersionUID = -1573481565177573283L;

        public int compare(Order bean1, Order bean2) {
            if (bean1.getId() > bean2.getId())
                return 1;
            else return -1;
        }
    }*/

    //private static final Comparator<Order> compareById = new CompareById();

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        log.debug("Commands starts");
        List<Order> ordersList = null;

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        ordersList = orderDao.getByUserId(user.getId());
        System.out.println("USER: in list orders" + user);
        List<Book> booksList = new ArrayList<>(ordersList.size());
        System.out.println("ORDERS LIST:" + ordersList);
        for (int i = 0; i < ordersList.size(); i++) {
            Book book = bookDao.get(ordersList.get(i).getBookId());
            System.out.println("Book id in foreach " + book);
            booksList.add(i, book);
        }

        log.trace("Found in DB: ordersList --> " + ordersList);

        /*if (ordersList != null) {
            ordersList.sort(compareById);
        }*/

        // put user order beans list to request
        request.setAttribute("ordersList", ordersList);
        request.setAttribute("booksOrderList", booksList);
        log.trace("Set the request attribute: ordersList --> " + ordersList);

        log.debug("Commands finished");
        return "/first/usermenu";
    }

}