package com.library.name.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        //Common commands
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("listOrders", new ListOrdersCommand());
        commands.put("catalog", new CatalogCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("orderBook", new OrderBookCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("updateSettings", new UpdateSettingsCommand());

        //User commands

        //Librarian commands
        commands.put("librarianMenu", new LibrarianMenuCommand());
        commands.put("userAbonement", new UserAbonementCommand());

        //Admin commands
        commands.put("addBook", new AddBookCommand());
        commands.put("addImage", new AddFileCommand());
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
