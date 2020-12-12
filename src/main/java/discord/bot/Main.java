package discord.bot;

import discord.bot.commands.*;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {
    public static final String token = ApiKey.token;

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllNonPrivilegedIntentsExcept(Intent.GUILD_WEBHOOKS).login().join();
    private static SortedMap<String, TemplateCommand> commands = new TreeMap<>();

    static {
        //commands.put("!mention", new AtMentionCommand());
        commands.put(FinalValues.PREFIX + FinalValues.CATFACT, new CatFactCommand());
        commands.put(FinalValues.PREFIX + FinalValues.CATIMAGE, new CatImage());
        commands.put(FinalValues.PREFIX + FinalValues.DELETE, new DeleteMessages());
        commands.put(FinalValues.PREFIX + FinalValues.HELPCOMMAND, new HelpCommand());
        commands.put(FinalValues.PREFIX + FinalValues.PINGCOMMAND, new PongCommand());
        commands.put(FinalValues.PREFIX + FinalValues.RANDOMMEME, new RadnomMeme());
        commands.put(FinalValues.PREFIX + FinalValues.RIOTSTATS, new RiotStats());
        commands.put(FinalValues.PREFIX + FinalValues.SILENCECOMMAND, new SilenceCommand());
        commands.put(FinalValues.PREFIX + FinalValues.TEMPCHANNEL, new TempChannel());
        commands.put(FinalValues.PREFIX + FinalValues.TFTCOMMAND, new TftCommand());
        commands.put(FinalValues.PREFIX + FinalValues.TRUMPCOMMAND, new TrumpQuoteCommand());
        commands.put(FinalValues.PREFIX + FinalValues.PLAY, new JoinBotCommand());
        commands.put(FinalValues.PREFIX + FinalValues.ODJEBI, new DisconnectCommand());
        commands.put(FinalValues.PREFIX + FinalValues.VOLUME, new VolumeCommand());
    }

    public static SortedMap<String, TemplateCommand> commands() {
        return Collections.unmodifiableSortedMap(commands);
    }

    public static void main(String[] args) {
        api.addMessageCreateListener(event -> {

            try {
                if (!event.getMessage().getUserAuthor().get().isBot()) {
                    String message = event.getMessageContent().toLowerCase();
                    String[] split = message.split(" ");
                    commands.get(split[0]).executeCommand(event);
                }
            } catch (NullPointerException ignored) {

            }
        });


    }

}
