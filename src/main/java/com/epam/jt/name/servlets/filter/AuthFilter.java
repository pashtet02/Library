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
    private static String url;
    private static String params;

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {


        switch (role) {
            case "ADMIN":
                req.getRequestDispatcher("/admin_menu.jsp").forward(req, res);
                break;
            case "LIBRARIAN":
                req.getRequestDispatcher("/librarian_menu.jsp").forward(req, res);
                break;
            case "USER":
                req.getRequestDispatcher("/user_menu.jsp").forward(req, res);
                break;
            default:
                req.getRequestDispatcher("/login.jsp").forward(req, res);
                break;
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
                nonNull(session.getAttribute("password"))){
                //session.getAttribute("isBanned") == "false") {

            final String role = (String) session.getAttribute("role");

            url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
            params = ((HttpServletRequest)servletRequest).getQueryString();

            moveToMenu(req, res, role.toUpperCase(Locale.ROOT));

        } else if (userDao.getUserByLoginAndPassword(login, password).getUsername() != null) {
            User user = userDao.getUserByLoginAndPassword(login, password);
            String role = user.getRole().toUpperCase();
            System.out.println("auth user:" + user);
            boolean isBanned = user.isBanned();

            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("user", user);
            System.out.println("IS BANNED " + isBanned);
            req.getSession().setAttribute("isBanned", Boolean.toString(isBanned));
            moveToMenu(req, res, role);

        } else {
            moveToMenu(req, res, "UNKNOWN");
        }
    }

    @Override
    public void destroy() {
    }

}
