package com.epam.jt.name.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        //Common commands
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("listOrders", new ListOrdersCommand());
        commands.put("catalog", new CatalogCommand());
        commands.put("logout", new LogoutCommand());
        //User commands

        //Librarian commands

        //Admin commands
        commands.put("addBook", new AddBookCommand());
        commands.put("editUser", new EditUserCommand());
        commands.put("usersList", new UsersListCommand());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

/**
     * Returns command object with the given name.
     *
     * @param commandName
     *            Name of the command.
     * @return Command object.
     */

    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
