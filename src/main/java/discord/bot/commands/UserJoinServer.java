package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class UserJoinServer {

    public static void executeCommand() {
        Server server = JoinBotCommand.server1;
        Main.api.addServerVoiceChannelMemberJoinListener(event -> {

            User user = event.getUser();
            if (!user.isBot()) {
                ServerTextChannel channel = event.getServer().getSystemChannel().get();
                channel.sendMessage("Boooooooooooooooook " + user.getMentionTag() + "!");
            }
        });
    }


}
