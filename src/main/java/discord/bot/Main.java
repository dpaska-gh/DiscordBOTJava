package discord.bot;

import discord.bot.commands.PongCommand;
import discord.bot.commands.CatFactCommand;
import discord.bot.commands.CatImage;
import discord.bot.commands.JoinBotCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class Main {
    public static final String token = "Nzc0NDA4OTI1MDkyNTExNzQ1.X6XWgw.dd9EiC578b8R6Vxhj-Qi56J-x4I";

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllNonPrivilegedIntentsExcept(Intent.GUILD_WEBHOOKS).login().join();

    public static void main(String[] args) {
        PongCommand pong = new PongCommand();
        pong.pongCommand();
        CatFactCommand cat = new CatFactCommand();
        cat.getCatFact();
        CatImage catimg = new CatImage();
        catimg.getCatImg();
        //discord.bot.mimiEasterEgg ee = new discord.bot.mimiEasterEgg();
        //ee.discord.bot.mimiEasterEgg();
        JoinBotCommand join = new JoinBotCommand();
        join.joinChannel();
    }

}
