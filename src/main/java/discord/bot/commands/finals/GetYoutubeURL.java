package discord.bot.commands.finals;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

public class GetYoutubeURL {
    public String a;


    public String getResult(MessageCreateEvent event) {
        String messageContent = event.getMessageContent();
        Server server = event.getServer().get();
        String[] split = messageContent.split(" ");

        if (split[0].equals(FinalValues.PREFIX + FinalValues.PLAY)) {
            this.a = split[1];
        }
        return getData(messageContent.replace(FinalValues.PREFIX + FinalValues.PLAY + " ", "").trim());
    }

    static String getData(String s) {
        if (s.split(" ").length > 1) {
            s = String.join(" ", s.split(" "));
            // System.out.println(s);
        }
        return s;
    }

}
