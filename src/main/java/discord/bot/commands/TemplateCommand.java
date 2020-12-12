package discord.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;

import java.util.List;

public interface TemplateCommand {

    void executeCommand(MessageCreateEvent event);

    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    String getCommandName();

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    List<String> getCommandDescription();


}
