package com.library.servlets;

import com.library.dao.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {


    private UserDao userDao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        userDao = UserDao.getInstance();
        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
