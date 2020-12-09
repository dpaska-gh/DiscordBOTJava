import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.concurrent.TimeUnit;

public class Main{
    public static final String token = "Nzc0NDA4OTI1MDkyNTExNzQ1.X6XWgw.GUWFQJeQ8sUwV3S5AwxlFsKwQPE";

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

    public static void main(String[] args) {
        PongCommand pong = new PongCommand();
        pong.pongCommand();
        catFactCommand cat = new catFactCommand();
        cat.getCatFact();
        catImage catimg = new catImage();
        catimg.getCatImg();

        api.addReactionAddListener(event -> {
            if (event.getEmoji().equalsEmoji("👎")) {
                event.deleteMessage();
            }
        }).removeAfter(30, TimeUnit.MINUTES);
    }

}
