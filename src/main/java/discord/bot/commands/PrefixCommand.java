package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class PrefixCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().contains(FinalValues.PREFIX + FinalValues.PREFIXCHANGE)) {
            Message message = event.getMessage();
            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");
            if (split[1].length() == 1) {
                FinalValues.setPREFIX(split[1]);
                event.getChannel().sendMessage("Prefix changed to " + FinalValues.PREFIX);
            } else {
                event.getChannel().sendMessage("Prefix should be one character");
            }
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.PREFIXCHANGE;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Changes the bot's prefix.");
    }
}
