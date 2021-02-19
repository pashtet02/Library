package com.library.name.web.command;

import com.library.name.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends Command{
    /**
     * Execution method for command.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     *                 this method invalidates session and return to login page
     * @return login.jsp
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session;

        session = request.getSession(false);
        if (session != null)
            session.invalidate();

        return Path.PAGE_LOGIN;
    }
}
