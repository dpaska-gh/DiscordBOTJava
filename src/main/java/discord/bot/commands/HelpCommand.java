package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class HelpCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        SortedMap<String, TemplateCommand> commands = Main.commands();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, TemplateCommand> commandName : commands.entrySet()) {
            sb.append(String.format("%-20s", commandName.getKey()));
            sb.append(String.format("%s", commandName.getValue().getCommandDescription().toString() + "\n"));
        }
        event.getChannel().sendMessage(sb.toString());
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
