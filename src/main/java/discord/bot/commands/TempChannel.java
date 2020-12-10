package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.server.Server;


import java.util.concurrent.TimeUnit;

import static discord.bot.Main.*;

public class TempChannel {

    public static void createTempChannel() {

        Main.api.addMessageCreateListener(eventListener -> {
            if (eventListener.getMessageContent().equalsIgnoreCase("!temp")) {
                Server server = api.getServerById("774334763653136384").get();
                ServerVoiceChannel channel = new ServerVoiceChannelBuilder(server)
                        .setName("Temporary Channel")
                        .setUserlimit(20)
                        .create()
                        .join();

                channel.addServerVoiceChannelMemberLeaveListener(event -> {
                    if (event.getChannel().getConnectedUserIds().isEmpty()) {
                        event.getChannel().delete();
                    }
                });

                api.getThreadPool().getScheduler().schedule(() -> {
                    if (channel.getConnectedUserIds().isEmpty()) {
                        channel.delete();
                    }
                }, 30, TimeUnit.SECONDS);
            }
        });
    }
}
