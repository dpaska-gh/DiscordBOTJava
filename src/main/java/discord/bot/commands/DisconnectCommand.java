package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class DisconnectCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        Server server = event.getServer().get();
        if (event.getMessage().getContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.ODJEBI)) {
            event.getChannel().sendMessage("Odjebi ti.");

            JoinBotCommand.trackScheduler.queue.clear();
            JoinBotCommand.PLAYER.destroy();

            server.getAudioConnection().get().close();

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
