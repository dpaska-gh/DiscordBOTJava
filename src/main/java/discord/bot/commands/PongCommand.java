package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;

import static discord.bot.Main.api;

public class PongCommand {

    public static void pongCommand() {
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + FinalValues.pingCommand)) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
