import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.util.concurrent.TimeUnit;

public class Main {
    public static final String token = "Nzc0NDA4OTI1MDkyNTExNzQ1.X6XWgw.dd9EiC578b8R6Vxhj-Qi56J-x4I";

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllNonPrivilegedIntentsExcept(Intent.GUILD_WEBHOOKS).login().join();

    public static void main(String[] args) {
        PongCommand pong = new PongCommand();
        pong.pongCommand();
        catFactCommand cat = new catFactCommand();
        cat.getCatFact();
        catImage catimg = new catImage();
        catimg.getCatImg();
        //mimiEasterEgg ee = new mimiEasterEgg();
        //ee.mimiEasterEgg();
        joinCommand join = new joinCommand();
        join.joinChannel();
    }

}
