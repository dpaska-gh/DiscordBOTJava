package discord.bot.commands.music;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class DisconnectCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        Server server;
        if (event.getServer().isPresent()) {
            server = event.getServer().get();
            if (event.getMessage().getContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.ODJEBI)) {
                User user;
                if (event.getMessage().getUserAuthor().isPresent()) {
                    user = event.getMessage().getUserAuthor().get();
                    if (user.getConnectedVoiceChannel(server).isEmpty()) {
                        event.getChannel().sendMessage("You have to be connected to a voice channel in order to use this command!");
                    } else {
                        event.getChannel().sendMessage("Odjebi ti.");
                        JoinBotCommand.trackScheduler.queue.clear();
                        JoinBotCommand.PLAYER.destroy();
                        if (server.getAudioConnection().isPresent())
                            server.getAudioConnection().get().close();
                    }
                }
            }
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.ODJEBI;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Disconnects the bot.");
    }
}
