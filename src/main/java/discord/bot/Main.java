package discord.bot;

import discord.bot.commands.*;
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
        commands.put("!catfact", new CatFactCommand());
        commands.put("!cat", new CatImage());
        commands.put("!delete", new DeleteMessages());
        commands.put("!help", new HelpCommand());
        commands.put("!ping", new PongCommand());
        commands.put("!meme", new RadnomMeme());
        commands.put("!riot", new RiotStats());
        commands.put("!silence", new SilenceCommand());
        commands.put("!temp", new TempChannel());
        commands.put("!tft", new TftCommand());
        commands.put("!trump", new TrumpQuoteCommand());
    }

    public static SortedMap<String, TemplateCommand> commands() {
        return Collections.unmodifiableSortedMap(commands);
    }

    public static void main(String[] args) {
        JoinBotCommand.joinChannel();
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
