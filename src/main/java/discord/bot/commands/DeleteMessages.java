package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;

public class DeleteMessages {

    public static void deleteMessages() {

        Main.api.addMessageCreateListener(event -> {


            Message message = event.getMessage();
            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");

            TextChannel channel = event.getChannel();

            for (FinalValues.deleteMessagesAlias alias : FinalValues.deleteMessagesAlias.values()) {
                if (split[0].equalsIgnoreCase(FinalValues.prefix + alias)) {
                    MessageSet messageSet = channel.getMessagesBefore(Integer.parseInt(split[1]), message).join();
                    Message.delete(Main.api, messageSet);
                    Message.delete(Main.api, message);
                }
            }

        });
    }
}
