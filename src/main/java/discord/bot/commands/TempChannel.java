package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static discord.bot.Main.api;

public class TempChannel implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {

        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.TEMPCHANNEL)) {
            Server server = event.getServer().get();
            ServerVoiceChannel channel = new ServerVoiceChannelBuilder(server)
                    .setName("Temporary Channel")
                    .setUserlimit(20)
                    .create()
                    .join();

            channel.addServerVoiceChannelMemberLeaveListener(eventListener -> {
                if (eventListener.getChannel().getConnectedUserIds().isEmpty()) {
                    eventListener.getChannel().delete();
                }
            });

            api.getThreadPool().getScheduler().schedule(() -> {
                if (channel.getConnectedUserIds().isEmpty()) {
                    channel.delete();
                }
            }, 30, TimeUnit.SECONDS);
        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.TEMPCHANNEL;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Creates a temporary voice channel for up to 20 people.");
    }
}
