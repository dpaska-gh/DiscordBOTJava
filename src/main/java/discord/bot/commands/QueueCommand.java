package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.QUEUE)) {
            AtomicInteger i = new AtomicInteger();

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(String.format("Now playing: **%s** \n", JoinBotCommand.PLAYER.getPlayingTrack().getInfo().title));

            JoinBotCommand.trackScheduler.queue.forEach(elements -> {
                i.getAndIncrement();
                stringBuilder.append(String.format("%s. %s \n", i.toString(), elements.getInfo().title));
            });

            event.getChannel().sendMessage(stringBuilder.toString());
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
