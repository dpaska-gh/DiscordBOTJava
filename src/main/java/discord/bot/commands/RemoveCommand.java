package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class RemoveCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        String[] s = event.getMessageContent().split(" ");
        if (s[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.REMOVE)) {
            if (s.length != 2) {
                event.getChannel().sendMessage("Too many/few arguments");
            } else {
                int i = 0;
                try {
                    if (Integer.parseInt(s[1]) <= JoinBotCommand.trackScheduler.queue.size() && Integer.parseInt(s[1]) > 0) {
                        String trackName = "";
                        for (AudioTrack audioTrack : JoinBotCommand.trackScheduler.queue) {
                            if (i == Integer.parseInt(s[1]) - 1) {
                                trackName = audioTrack.getInfo().title;
                                JoinBotCommand.trackScheduler.queue.remove(audioTrack);
                            }
                            i++;
                        }
                        event.getChannel().sendMessage("**" + trackName + "** has been removed.");
                    } else {
                        event.getChannel().sendMessage("You cannot remove a track that isn't in the queue");
                    }
                } catch (NumberFormatException a) {
                    event.getChannel().sendMessage("Second argument has to be a number.");
                }
            }
        }
    }

    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    @Override
    public String getCommandName() {
        return FinalValues.getREMOVE();
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Removes selected track from the queue.");
    }
}
