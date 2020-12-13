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
        SortedMap<String, TemplateCommand> commands = Main.setCommands();
        StringBuilder sb = new StringBuilder();

        String[] split = event.getMessageContent().split(" ");

        if (FinalValues.getPREFIX().equalsIgnoreCase(split[0].substring(0, 1))) {

            if (split.length == 1)
                for (Map.Entry<String, TemplateCommand> commandName : commands.entrySet()) {
                    sb.append(String.format("%-20s", commandName.getKey()));
                    sb.append(String.format("%s", commandName.getValue().getCommandDescription().toString() + "\n"));
                }

            else if (split.length == 2) {
                if (commands.get(FinalValues.getPREFIX() + split[1]) != null)
                    sb.append(String.format(FinalValues.getPREFIX() + "%-20s %s", split[1], commands.get(FinalValues.getPREFIX() + split[1]).getCommandDescription()));
            } else sb.append("Unknown command.");
        } else {
            event.getChannel().sendMessage("Wrong prefix");
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
