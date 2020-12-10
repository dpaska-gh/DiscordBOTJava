package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.util.Collection;

public class SilenceCommand {
    public static void silenceCommand() {
        Main.api.addMessageCreateListener(event -> {

            Message message = event.getMessage();
            String messageContent = event.getMessageContent();
            Server server = event.getServer().get();
            String[] split = messageContent.split(" ");

            TextChannel channel = event.getChannel();
            ServerVoiceChannel voiceChannel = event.getMessageAuthor().getConnectedVoiceChannel().get();

            if (split[0].equalsIgnoreCase("!silence")) {
                Collection<User> userCollection = voiceChannel.getConnectedUsers();
                userCollection.forEach(users -> {
                    if (users.getName().toUpperCase().equals(split[1].toUpperCase())) {
                        users.mute(server);
                    }
                });
            }

        });
    }
}
