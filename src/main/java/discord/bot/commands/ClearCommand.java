package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class ClearCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.CLEAR))
            JoinBotCommand.trackScheduler.queue.clear();
        event.getChannel().sendMessage("The queue has been cleared");
    }

    @Override
    public String getCommandName() {
        return FinalValues.CLEAR;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Joins the Musicbot.");
    }
}
