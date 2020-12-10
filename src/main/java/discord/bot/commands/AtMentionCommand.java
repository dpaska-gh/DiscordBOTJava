package discord.bot.commands;

import discord.bot.Main;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;

import java.util.List;

public class AtMentionCommand {


    public void onMessageCreate() {

        Main.api.addMessageCreateListener(event -> {
            Message message = event.getMessage();
            List<User> users = message.getMentionedUsers();

            if (!users.isEmpty()) {
                event.getChannel().sendMessage("koje vej");
            }
        });
    }
}
