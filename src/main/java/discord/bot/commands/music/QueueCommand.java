package discord.bot.commands.music;


import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class QueueCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.QUEUE)) {

            event.getChannel().sendMessage(BotEmbeds.musicQueueEmbed(JoinBotCommand.trackScheduler.queue));

        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.QUEUE;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Shows the queue of the music bot.");
    }
}
