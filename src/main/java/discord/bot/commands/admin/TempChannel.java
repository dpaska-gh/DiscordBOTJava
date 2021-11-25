package discord.bot.commands.admin;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.BotEmbeds;
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

        if (event.getMessageContent().startsWith(FinalValues.PREFIX + FinalValues.TEMPCHANNEL)) {
            Server server = event.getServer().get();
            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");
            String s = messageContent.replace(FinalValues.PREFIX + FinalValues.TEMPCHANNEL, "");
            ServerVoiceChannel channel;

            if (split.length == 2) {

                channel = new ServerVoiceChannelBuilder(server)
                        .setName(split[1])
                        .setUserlimit(20)
                        .create()
                        .join();
            } else if (split.length == 1) {
                channel = new ServerVoiceChannelBuilder(server)
                        .setName(FinalValues.TEMPDEFAULT)
                        .setUserlimit(20)
                        .create()
                        .join();
            } else {
                channel = new ServerVoiceChannelBuilder(server)
                        .setName(getString(s))
                        .setUserlimit(20)
                        .create()
                        .join();
            }

            event.getChannel().sendMessage(BotEmbeds.tempEmbed(channel.getName()));

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

    public static String getString(String s) {
        if (s.split(" ").length > 1) {
            s = String.join(" ", s.split(" "));
        }
        return s;
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
