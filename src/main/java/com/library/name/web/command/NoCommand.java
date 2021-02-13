package com.library.name.web.command;

import com.library.name.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand extends Command {

    private static final long serialVersionUID = -2785976616686657267L;

    private static final Logger log = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");

        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        log.error("Set the request attribute: errorMessage --> " + errorMessage);

        log.debug("Command finished");
        return Path.PAGE_ERROR_PAGE;
    }

}