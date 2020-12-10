package discord.bot.commands;

import discord.bot.Main;

public class PongCommand extends Main {

    public void pongCommand(){
        api.addMessageCreateListener(event -> {
            if(event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
