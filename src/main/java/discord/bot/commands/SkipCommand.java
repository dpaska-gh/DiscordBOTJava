package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class SkipCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.SKIP)) {
            JoinBotCommand.trackScheduler.nextTrack();
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.SKIP;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Skips the current track");
    }
}
