package com.epam.jt.name.servlets.filter;


import com.epam.jt.name.dao.UserDao;
import com.epam.jt.name.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import static java.util.Objects.nonNull;


@WebFilter
public class AuthFilter implements Filter {
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {


        if (role.equals("LIBRARIAN")) {
            req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);
        }
        else if (role.equals("USER")) {
            req.getRequestDispatcher("/user_menu.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final UserDao userDao = (UserDao) req.getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();

        //Logged user.
        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {

            final String role = (String) session.getAttribute("role");

            moveToMenu(req, res, role.toUpperCase(Locale.ROOT));

        } else if (userDao.getUserByLoginAndPassword(login, password).getUsername() != null) {
            User user = userDao.getUserByLoginAndPassword(login, password);
            String role = user.getRole().toUpperCase();

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("user", user);
            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, "UNKNOWN");
        }
    }

    @Override
    public void destroy() {
    }

}
