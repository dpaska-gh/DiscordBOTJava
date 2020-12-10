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
            if (event.getMessage().getContent().contains(FinalValues.prefix + FinalValues.silenceCommandAlias.tisina)
                    || event.getMessage().getContent().contains(FinalValues.prefix + FinalValues.silenceCommandAlias.psst)
                    || event.getMessage().getContent().contains(FinalValues.prefix + FinalValues.silenceCommandAlias.silence)) {

                String messageContent = event.getMessageContent();
                Server server = event.getServer().get();
                String[] split = messageContent.split(" ");
                ServerVoiceChannel voiceChannel = event.getMessageAuthor().getConnectedVoiceChannel().get();
                Collection<User> userCollection = voiceChannel.getConnectedUsers();

                if (split[0].equalsIgnoreCase(FinalValues.prefix + FinalValues.silenceCommandAlias.tisina)
                        || split[0].equalsIgnoreCase(FinalValues.prefix + FinalValues.silenceCommandAlias.psst)
                        || split[0].equalsIgnoreCase(FinalValues.prefix + FinalValues.silenceCommandAlias.silence)) {
                    userCollection.forEach(users -> {
                        if (users.getName().toUpperCase().contains(split[1].toUpperCase())) {
                            users.mute(server);
                        }
                    });
                }
            }
        });
    }
}
