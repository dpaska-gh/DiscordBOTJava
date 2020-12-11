package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SilenceCommand implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {

        if (event.getMessageContent().contains(FinalValues.PREFIX + FinalValues.SILENCECOMMAND)) {
            String messageContent = event.getMessageContent();
            Server server = event.getServer().get();
            String[] split = messageContent.split(" ");
            ServerVoiceChannel voiceChannel = event.getMessageAuthor().getConnectedVoiceChannel().get();
            Collection<User> userCollection = voiceChannel.getConnectedUsers();

            if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.SILENCECOMMAND)) {
                userCollection.forEach(users -> {
                    if (users.getName().toUpperCase().contains(split[1].toUpperCase())) {
                        users.mute(server);

                        if (users.isMuted(server)) {
                            users.unmute(server);
                        }
                    }
                });
            }

        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.SILENCECOMMAND;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Command to silence someone.");
    }
}
