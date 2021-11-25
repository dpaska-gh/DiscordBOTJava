package discord.bot.commands.music;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class ClearCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.CLEAR)) {
            try {
                if (JoinBotCommand.user.isConnected(JoinBotCommand.audioConnection.getChannel())) {
                    if (!JoinBotCommand.trackScheduler.queue.isEmpty()) {
                        JoinBotCommand.trackScheduler.queue.clear();
                        event.getChannel().sendMessage("The queue has been cleared");
                    } else {
                        event.getChannel().sendMessage("Nothing to clear");
                    }
                } else
                    event.getChannel().sendMessage("You and the bot have to be connected to a voice channel to use this command");
            } catch (NullPointerException e) {
                event.getChannel().sendMessage("You and the bot have to be connected to a voice channel to use this command");
            }
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.CLEAR;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Clears the current queue.");
    }
}
