package discord.bot.commands.messages;

import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class HiCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.HI)) {

            if (event.getMessage().getUserAuthor().isPresent())
                event.getChannel().sendMessage("Boooooooooooooooook " + event.getMessage().getUserAuthor().get().getMentionTag() + "!");
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.HI;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Says hi back to user.");
    }
}
