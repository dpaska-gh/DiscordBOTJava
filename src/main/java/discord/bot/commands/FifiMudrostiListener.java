package discord.bot.commands;

import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

public class FifiMudrostiListener {
    public static void executeFifiMudrosti(MessageCreateEvent event) {

        ServerTextChannel textChannel = event.getServerTextChannel().get();
        if (textChannel.getName().equalsIgnoreCase("fifijeve-mudrosti")) {
            event.getMessage().pin();
        }

    }
}
