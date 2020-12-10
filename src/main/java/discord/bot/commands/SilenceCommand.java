package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.util.Collection;

public class SilenceCommand {
    public static void silenceCommand() {
        Main.api.addMessageCreateListener(event -> {
            for (FinalValues.silenceCommandAlias alias : FinalValues.silenceCommandAlias.values()) {
                if (event.getMessageContent().contains(FinalValues.prefix + alias)) {
                    String messageContent = event.getMessageContent();
                    Server server = event.getServer().get();
                    String[] split = messageContent.split(" ");
                    ServerVoiceChannel voiceChannel = event.getMessageAuthor().getConnectedVoiceChannel().get();
                    Collection<User> userCollection = voiceChannel.getConnectedUsers();

                    if (split[0].equalsIgnoreCase(FinalValues.prefix + alias)) {
                        userCollection.forEach(users -> {
                            if (users.getName().toUpperCase().contains(split[1].toUpperCase())) {
                                users.mute(server);
                            }
                        });
                    }
                }
            }

        });
    }
}
