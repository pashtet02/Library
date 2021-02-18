package com.library.name.web.command;

import com.library.name.web.command.admin.AddBookCommand;
import com.library.name.web.command.admin.AddFileCommand;
import com.library.name.web.command.admin.EditBookCommand;
import com.library.name.web.command.admin.EditUserCommand;
import com.library.name.web.command.librarian.LibrarianMenuCommand;
import com.library.name.web.command.librarian.UserAbonementCommand;
import com.library.name.web.command.user.AddReviewCommand;
import com.library.name.web.command.user.ListOrdersCommand;
import com.library.name.web.command.user.OrderBookCommand;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private CommandContainer(){
        //hello
    }

    private static final Logger log = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        //Common commands
        commands.put("login", new LoginCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("catalog", new CatalogCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("registration", new RegistrationCommand());



        //User commands
        commands.put("orderBook", new OrderBookCommand());
        commands.put("viewSettings", new ViewSettingsCommand());
        commands.put("updateSettings", new UpdateSettingsCommand());
        commands.put("listOrders", new ListOrdersCommand());
        commands.put("addReview", new AddReviewCommand());


        //Librarian commands
        commands.put("librarianMenu", new LibrarianMenuCommand());
        commands.put("userAbonement", new UserAbonementCommand());

        //Admin commands
        commands.put("addBook", new AddBookCommand());
        commands.put("editBook", new EditBookCommand());
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
