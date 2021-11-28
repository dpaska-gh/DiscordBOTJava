package discord.bot;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.CommandsMap;
import discord.bot.commands.listeners.FifiMudrostiListener;
import discord.bot.commands.listeners.NewMemberListener;
import discord.bot.commands.listeners.UserJoinServerListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.io.IOException;
import java.util.SortedMap;
import java.util.Timer;


public class Main {
    public static final String token = ApiKey.token;

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllIntents().login().join();

    private static SortedMap<String, TemplateCommand> comm;

    public static void main(String[] args) {
// Enable debug logging
        FallbackLoggerConfiguration.setDebug(true);

// Enable trace logging
        FallbackLoggerConfiguration.setTrace(true);
        api.setMessageCacheSize(10, 60 * 60);

        //tft stats based on timer.
        Timer time = new Timer();
        TimedTFT timedTFT = new TimedTFT();
        time.schedule(timedTFT, 0, 3600000);

        //message create event
        api.addMessageCreateListener(event -> {
            try {
                if (event.getMessage().getUserAuthor().isPresent())
                    if (!event.getMessage().getUserAuthor().get().isBot()) {
                        String message = event.getMessageContent().toLowerCase();
                        String[] split = message.split(" ");
                        comm = CommandsMap.setCommands();
                        comm.get(split[0]).executeCommand(event);
                    }
            } catch (NullPointerException | IOException | InterruptedException ignored) {

            }
            FifiMudrostiListener.executeFifiMudrosti(event);
        });

        UserJoinServerListener.executeCommand();
        NewMemberListener.executeCommand();
    }

}
