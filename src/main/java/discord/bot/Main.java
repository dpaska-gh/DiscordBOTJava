package discord.bot;

import discord.bot.commands.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.io.IOException;

public class Main {
    public static final String token = "Nzc0NDA4OTI1MDkyNTExNzQ1.X6XWgw.8ISdCJvLOqLl4mWIohoxnRvIR0g";

    public static final DiscordApi api = new DiscordApiBuilder().setToken(token).setAllNonPrivilegedIntentsExcept(Intent.GUILD_WEBHOOKS).login().join();

    public static void main(String[] args) throws IOException {
        PongCommand pong = new PongCommand();
        pong.pongCommand();
        CatFactCommand cat = new CatFactCommand();
        cat.getCatFact();
        CatImage catimg = new CatImage();
        catimg.getCatImg();
        TempChannel channel = new TempChannel();
        channel.createTempChannel();

        DeleteMessages deleteMessages = new DeleteMessages();
        deleteMessages.deleteMessages();

        AtMentionCommand mentionCommand = new AtMentionCommand();
        mentionCommand.onMessageCreate();

        RiotStats riotStats = new RiotStats();
        riotStats.getStats();

        TftCommand tftCommand=new TftCommand();
        tftCommand.getTft();

        //discord.bot.mimiEasterEgg ee = new discord.bot.mimiEasterEgg();
        //ee.discord.bot.mimiEasterEgg();
        JoinBotCommand join = new JoinBotCommand();
        join.joinChannel();
    }

}
