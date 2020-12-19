package discord.bot;

import discord.bot.commands.*;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;


public class Main {
    public static final String token = ApiKey.token;

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllIntents().login().join();

    private static SortedMap<String, TemplateCommand> comm;

    public static SortedMap<String, TemplateCommand> setCommands() {
        //commands.put("!mention", new AtMentionCommand());
        SortedMap<String, TemplateCommand> commands = new TreeMap<>();
        commands.put(FinalValues.getPREFIX() + FinalValues.CATFACT, new CatFactCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.CATIMAGE, new CatImage());
        commands.put(FinalValues.getPREFIX() + FinalValues.DELETE, new DeleteMessages());
        commands.put(FinalValues.getPREFIX() + FinalValues.HELPCOMMAND, new HelpCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.PINGCOMMAND, new PongCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.RANDOMMEME, new RadnomMeme());
        commands.put(FinalValues.getPREFIX() + FinalValues.RIOTSTATS, new RiotStats());
        commands.put(FinalValues.getPREFIX() + FinalValues.SILENCECOMMAND, new SilenceCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.TEMPCHANNEL, new TempChannel());
        commands.put(FinalValues.getPREFIX() + FinalValues.TFTCOMMAND, new TftCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.TRUMPCOMMAND, new TrumpQuoteCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.PLAY, new JoinBotCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.ODJEBI, new DisconnectCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.VOLUME, new VolumeCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.SKIP, new SkipCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.QUEUE, new QueueCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.SET, new SetCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.HI, new HiCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.CLEAR, new ClearCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.COVID, new CovidCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.NASA, new NasaPlanetCommand());
        return commands;
    }

    public static SortedMap<String, TemplateCommand> commands() {
        return Collections.unmodifiableSortedMap(comm);
    }

    public static void main(String[] args) {
// Enable debug logging
        FallbackLoggerConfiguration.setDebug(false);

// Enable trace logging
        FallbackLoggerConfiguration.setTrace(false);

        api.addMessageCreateListener(event -> {
            try {
                if (event.getMessage().getUserAuthor().isPresent())
                    if (!event.getMessage().getUserAuthor().get().isBot()) {
                        String message = event.getMessageContent().toLowerCase();
                        String[] split = message.split(" ");
                        comm = setCommands();
                        comm.get(split[0]).executeCommand(event);
                    }
            } catch (NullPointerException | IOException | InterruptedException ignored) {

            }
            FifiMudrostiListener.executeFifiMudrosti(event);
        });

        UserJoinServer.executeCommand();
        NewMemberListener.executeCommand();
    }

}
