package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

public class UserJoinServer {

    public static void executeCommand() {

        Main.api.addServerVoiceChannelMemberJoinListener(event -> {
            User user = event.getUser();
            TextChannel channel = event.getServer().getTextChannelById(774367213258801211L).get();
           channel.sendMessage("Boooooooooooooooook " + user.getMentionTag() + "!");
        });
    }


}
