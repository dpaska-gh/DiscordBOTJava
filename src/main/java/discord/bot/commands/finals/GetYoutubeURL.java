package discord.bot.commands.finals;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

public class GetYoutubeURL {
    public String a;


    public String getURL(MessageCreateEvent event) {
        String messageContent = event.getMessageContent();
        Server server = event.getServer().get();
        String[] split = messageContent.split(" ");

        if (split[0].equals(FinalValues.PREFIX + FinalValues.PLAY)) {
            this.a = split[1];
        }
        return a;
    }

}
