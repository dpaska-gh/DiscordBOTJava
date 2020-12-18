package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class SkipCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {

        boolean x = true;

        if (event.getMessageContent().startsWith(FinalValues.PREFIX + FinalValues.SKIP)) {
            User bot = Main.api.getYourself();
            User user = event.getMessageAuthor().asUser().get();
            ServerVoiceChannel channel = JoinBotCommand.audioConnection.getChannel();
            Server server = event.getServer().get();
            String[] split = event.getMessageContent().split(" ");

            if (user.isConnected(channel)) {

                if (bot.isConnected(channel) && split.length == 1) {
                    if (JoinBotCommand.trackScheduler.queue.isEmpty() && TrackScheduler.isStarted)
                        event.getChannel().sendMessage("The track has been skipped.");
                    else {
                        if (JoinBotCommand.trackScheduler.queue.isEmpty())
                            event.getChannel().sendMessage("Nothing to skip!");
                    }
                }

                if (JoinBotCommand.trackScheduler.queue.size() == 0 && bot.isConnected(channel) && TrackScheduler.isStarted) {
                    DisconnectOnFinish.onFinish();
                }

                if (split.length == 1) {
                    JoinBotCommand.trackScheduler.nextTrack();
                    if (JoinBotCommand.trackScheduler.queue.isEmpty() && !TrackScheduler.isStarted)
                        JoinBotCommand.PLAYER.destroy();
                } else if (split.length == 2) {
                    try {
                        Integer num = Integer.parseInt(split[1]);

                        if (num > JoinBotCommand.trackScheduler.queue.size())
                            throw new NumberFormatException();

                        System.out.println(num);
                        for (int i = 0; i < num; i++) {
                            JoinBotCommand.trackScheduler.nextTrack();
                        }
                        event.getChannel().sendMessage("Skipped " + num + " tracks.");

                    } catch (NumberFormatException ex) {
                        event.getChannel().sendMessage("Wrong second argument of skip command.");
                        x = false;
                    }
                } else event.getChannel().sendMessage("Too many arguments given for skip command.");

                if (x)
                    event.getChannel().sendMessage(BotEmbeds.skipMusicEmbed(JoinBotCommand.PLAYER.getPlayingTrack()));

            } else if (!user.isConnected(channel)) {
                event.getChannel().sendMessage("You have to be connected to a channel to use this command");
            }
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
