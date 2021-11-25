package discord.bot.commands.listeners;

import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

public class FifiMudrostiListener {
    public static void executeFifiMudrosti(MessageCreateEvent event) {
        ServerTextChannel textChannel;
        if (event.getServerTextChannel().isPresent()) {
            textChannel = event.getServerTextChannel().get();
            if (textChannel.getName().equalsIgnoreCase("fifijeve-mudrosti")) {
                event.getMessage().pin();
            }
        }
    }
}
