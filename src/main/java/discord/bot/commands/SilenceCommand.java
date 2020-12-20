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
            Collection<User> userCollection;
            String messageContent = event.getMessageContent();
            Server server = event.getServer().get();
            String[] split = messageContent.split(" ");
            ServerVoiceChannel voiceChannel;

            if (event.getMessageAuthor().getConnectedVoiceChannel().isPresent()) {
                voiceChannel = event.getMessageAuthor().getConnectedVoiceChannel().get();
                userCollection = voiceChannel.getConnectedUsers();
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

            } else {
                event.getChannel().sendMessage("You have to be in the same channel as the targeted user.");
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
