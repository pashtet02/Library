package com.library.name.web.command;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

public class SettingsCommand extends Command{

    private static final Logger log = Logger.getLogger(EditUserCommand.class);
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //changeLocale mapping
        String locale = request.getParameter("locale");
        log.trace("LOCALE CHANGED --> " + locale);
        HttpSession session = request.getSession();
        if (locale != null && !locale.isEmpty()) {
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);

            session.setAttribute("defaultLocale", locale);
            log.trace("Set the session attribute: defaultLocaleName --> " + locale);
            System.out.println("LOCALE LOGIN COMMAND: " + locale);
            log.info("Locale for user: defaultLocale --> " + locale);
        }
        return "/controller?command=catalog";
    }
}
