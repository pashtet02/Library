package com.epam.jt.name.servlets.filter;

import com.epam.jt.name.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private String locale;

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        if (request.getParameter("locale") != null && !request.getParameter("locale").isEmpty()) {
            locale = req.getParameter("locale");
            final HttpSession session = req.getSession();

            session.setAttribute("locale", locale);
            System.out.println("Locale = " + locale);

        }
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {

    }

    public void destroy() {
        //hello
    }
}