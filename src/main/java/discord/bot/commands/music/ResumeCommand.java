package discord.bot.commands.music;

import discord.bot.Main;
import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class ResumeCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException {
        ServerVoiceChannel channel = null;
        if (event.getMessageAuthor().getConnectedVoiceChannel().isPresent()) {
            channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
        }
        if (Main.api.getYourself().isConnected(channel) && event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.RESUME)) {
            if (JoinBotCommand.PLAYER.isPaused())
                JoinBotCommand.PLAYER.setPaused(false);
        }
    }

    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    @Override
    public String getCommandName() {
        return FinalValues.RESUME;
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Resumes the musicbot");
    }
}
