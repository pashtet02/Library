package com.epam.jt.name.servlets;

import com.epam.jt.name.dao.DBManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    /**
     * Fake database connector.
     */
    private DBManager dbManager;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        dbManager = DBManager.getInstance();

        dbManager.getUserByLoginAndPassword("Cardinal", "1111");
        dbManager.getUserByLoginAndPassword("Pashtet", "1111");

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        servletContext.setAttribute("dao", dbManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}