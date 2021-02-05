/*
package com.epam.jt.name.servlets.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session;

        session = req.getSession(false);
        if (session != null)
            session.invalidate();

        resp.sendRedirect(super.getServletContext().getContextPath());
    }

}*/
