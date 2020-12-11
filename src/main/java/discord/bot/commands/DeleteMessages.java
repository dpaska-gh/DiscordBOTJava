package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class DeleteMessages implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {

        Message message = event.getMessage();
        String messageContent = event.getMessageContent();
        String[] split = messageContent.split(" ");

        TextChannel channel = event.getChannel();

        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.DELETE)) {
            MessageSet messageSet = channel.getMessagesBefore(Integer.parseInt(split[1]), message).join();
            Message.delete(Main.api, messageSet);
            Message.delete(Main.api, message);
        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.DELETE;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Deletes the given number of messages");
    }
}
