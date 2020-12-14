package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class SkipCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {

        if (event.getMessageContent().startsWith(FinalValues.PREFIX + FinalValues.SKIP)) {

            String[] split = event.getMessageContent().split(" ");

            if (JoinBotCommand.trackScheduler.queue.size() == 0)
                DisconnectOnFinish.onFinish();

            if (split.length == 1) {
                JoinBotCommand.trackScheduler.nextTrack();
                event.getChannel().sendMessage("The track has been skipped.");


            } else if (split.length == 2) {
                try {
                    Integer num = Integer.parseInt(split[1]);

                    if (num == 1|| num>=JoinBotCommand.trackScheduler.queue.size()) throw new NumberFormatException();

                    System.out.println(num);
                    for (int i = 0; i < num; i++) {
                        JoinBotCommand.trackScheduler.nextTrack();
                    }
                    event.getChannel().sendMessage("Skipped " + num + " tracks.");
                } catch (NumberFormatException ex) {
                    event.getChannel().sendMessage("Wrong second argument of skip command.");
                }
            } else event.getChannel().sendMessage("Too many arguments given for skip command.");
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.SKIP;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Skips the current track");
    }
}
