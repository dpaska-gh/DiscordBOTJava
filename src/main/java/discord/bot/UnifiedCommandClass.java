package discord.bot;

import discord.bot.commands.*;

public class UnifiedCommandClass {
    public static void commandStartup(){
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
    }
}
