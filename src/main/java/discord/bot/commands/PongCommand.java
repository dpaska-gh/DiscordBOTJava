package discord.bot.commands;

import static discord.bot.Main.*;

public class PongCommand {

    public void pongCommand() {
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
