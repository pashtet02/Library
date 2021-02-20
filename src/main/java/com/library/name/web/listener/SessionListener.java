package com.library.name.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class SessionListener implements HttpSessionListener {

    /**
     * Default constructor.
     */
    public SessionListener() {
        //hello
    }

    private ServletContext ctx = null;
    private static int current = 0;

    public void sessionCreated(HttpSessionEvent e) {
        current++;

        ctx = e.getSession().getServletContext();
        ctx.setAttribute("currentUsers", current);
    }

    public void sessionDestroyed(HttpSessionEvent e) {
        current--;
        ctx.setAttribute("currentUsers", current);
    }
}
