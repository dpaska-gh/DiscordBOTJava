package discord.bot.commands.messages;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class PongCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.PINGCOMMAND)) {
            event.getChannel().sendMessage("Pong!");
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.PINGCOMMAND;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Ping-pong!");
    }
}
