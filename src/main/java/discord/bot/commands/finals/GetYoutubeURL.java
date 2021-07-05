package discord.bot.commands.finals;

import org.javacord.api.event.message.MessageCreateEvent;

public class GetYoutubeURL {

    public String getResult(MessageCreateEvent event) {
        String messageContent = event.getMessageContent();
        return getData(messageContent.replace(FinalValues.PREFIX + FinalValues.PLAY + " ", "").trim());
    }

    static String getData(String s) {
        if (s.split(" ").length > 1) {
            s = String.join(" ", s.split(" "));
        }
        return s;
    }

}
