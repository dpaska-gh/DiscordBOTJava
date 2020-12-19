package discord.bot.commands;

import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class HelpCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        String[] split = event.getMessageContent().split(" ");

        if (split.length == 1)
            event.getChannel().sendMessage(BotEmbeds.helpEmbed(true, null));
        else if (split.length == 2) {
            event.getChannel().sendMessage(BotEmbeds.helpEmbed(false, split[1]));
        } else {
            event.getChannel().sendMessage(new EmbedBuilder().setTitle("Help command").setDescription("Invalid ammount of arguments"));
        }


        //event.getChannel().sendMessage(sb.toString());

    }

    @Override
    public String getCommandName() {
        return FinalValues.HELPCOMMAND;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Gives all commands wth their descriptions");
    }
}
