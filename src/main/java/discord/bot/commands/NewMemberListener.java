package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;

import java.util.Collection;

public class NewMemberListener {

    public static void executeCommand() {
        Collection<Server> servers = Main.api.getServers();
        servers.forEach(server -> {
            server.addServerMemberJoinListener(event -> {
                TextChannel channel = event.getServer().getSystemChannel().get();
                channel.sendMessage("Welcome to the server, " + event.getUser().getMentionTag() + "!");
            });

        });

    }

}
