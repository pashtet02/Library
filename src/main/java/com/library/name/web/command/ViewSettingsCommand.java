package com.library.name.web.command;

import com.library.name.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * View settings command.
 * 
 *
 */
public class ViewSettingsCommand extends Command {
	private static final Logger log = Logger.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {		
		log.debug("Command starts");
		
		log.debug("Command finished");
		return Path.PAGE_SETTINGS;
	}

}