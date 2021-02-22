package com.library.name.service;

import com.library.name.dao.BookDao;
import com.library.name.dao.OrderDao;
import com.library.name.dao.UserDao;
import com.library.name.entity.Book;
import com.library.name.entity.Order;
import com.library.name.entity.User;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;


public class MailSender {
    private static final Logger logger = Logger.getLogger(MailSender.class);

    private MailSender() {
        //hello
    }

    public static void sendMail(HttpServletRequest request, HttpServletResponse response) {
        long orderId = Long.parseLong(request.getParameter("orderId"));

        OrderDao orderDao = OrderDao.getInstance();
        Order order = orderDao.get(orderId);

        UserDao userDao = UserDao.getInstance();
        User user = userDao.get(order.getUserId());

        BookDao bookDao = BookDao.getInstance();
        Book book = bookDao.get(order.getBookId());


        StringBuilder sb = new StringBuilder();
        sb.append("<div>Hello!  " + user.getFirstName() + "!</div>");

        sb.append("<div style=\"border: 1px solid grey;  border-radius: 5px;\">");
        sb.append("<div>Your order on book: " + book.getTitle() + " was <strong>" + order.getStatus() + "</strong></div>");
        sb.append("<div>Librarian comment: " + order.getLibrarianComment() + "</div>");
        sb.append("<div>Return date: " + order.getReturnDate() + "</div>");
        sb.append("<div style=\"text-align: right; padding:10px;\"></p></div></div>");
        sb.append("<div> <a href=\"http://localhost:8080/library/controller?command=catalog&page=1\">Go to library</a> </div>");
        sb.append("<div>Have a nice day!</div>");


        final String username = "chodak2014ua@gmail.com";
        final String password = "25012002";

        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();


        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        // Get the default Session object.
        Session mailSession = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        mailSession.setDebug(true);

        response.setContentType("text/html");

        try {

            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("chodak2014ua@gmail.com"));

            message.setRecipients(
                    Message.RecipientType.TO,
                    //InternetAddress.parse(user.getEmail())
                    InternetAddress.parse(user.getMail())
            );
            message.setSubject("Book order");
            message.setContent(sb.toString(), "text/html;charset=UTF-8");
            Transport.send(message);
            logger.info("Mail sent successfully to user " + user.getMail());


        } catch (MessagingException mex) {
            logger.error(mex.getMessage());
            mex.printStackTrace();
        }

    }
}