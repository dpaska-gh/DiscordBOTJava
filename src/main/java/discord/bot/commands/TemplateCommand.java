package discord.bot.commands;

import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.List;

public interface TemplateCommand {

    void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException;

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
