package com.epam.jt.name;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/my")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //resp.setContentType("text/html");
        RequestDispatcher view = req.getRequestDispatcher("src/main/webapp/my.html");
        // don't add your web-app name to the path

        try {
            view.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
