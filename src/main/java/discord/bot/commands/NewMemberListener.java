package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.TextChannel;

import java.util.Optional;

public class NewMemberListener {

    public static void executeCommand() {
        Main.api.addServerMemberJoinListener(event -> {
            Optional<TextChannel> channel = Main.api.getTextChannelById(774367293492822056L);
            System.out.println("(\"Welcome to the server, \" + event.getUser().getMentionTag() + \"!\")");
            channel.ifPresent(textChannel -> textChannel.sendMessage("Welcome to the server, " + event.getUser().getMentionTag() + "!"));
        });
    }
}
