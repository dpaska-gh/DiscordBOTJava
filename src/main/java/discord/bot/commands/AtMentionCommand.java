package discord.bot.commands;

import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class AtMentionCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {

        Message mess = event.getMessage();
        List<User> users = mess.getMentionedUsers();

        if (!users.isEmpty()) {
            event.getChannel().sendMessage("koje vej");
        }

    }

    @Override
    public String getCommandName() {
        return "mention";
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Command replys on person mention.");
    }
}
