package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.TextChannel;

public class NewMemberListener {

    public static void executeCommand() {

        Main.api.addServerMemberJoinListener(event -> {

            TextChannel channel = event.getServer().getTextChannelById(774367213258801211L).get();
            System.out.println("(\"Welcome to the server, \" + event.getUser().getMentionTag() + \"!\")");
            channel.sendMessage("Welcome to the server, " + event.getUser().getMentionTag() + "!");

        });
    }
}
