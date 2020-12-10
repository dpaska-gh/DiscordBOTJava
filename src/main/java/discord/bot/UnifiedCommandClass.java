package discord.bot;

import discord.bot.commands.*;

import java.io.IOException;

public class UnifiedCommandClass {
    public static void commandStartup() throws IOException {
        AtMentionCommand.onMessageCreate();
        CatFactCommand.getCatFact();
        CatImage.getCatImg();
        DeleteMessages.deleteMessages();
        JoinBotCommand.joinChannel();
        PongCommand.pongCommand();
        RadnomMeme.randomMeme();
        TempChannel.createTempChannel();
        TrumpQuoteCommand.trumpQuoteCommand();
        SilenceCommand.silenceCommand();
        TftCommand.getTft();
        RiotStats.getStats();
    }
}
