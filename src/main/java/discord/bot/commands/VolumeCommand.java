package discord.bot.commands;

import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class VolumeCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {

        if (event.getMessageContent().contains(FinalValues.PREFIX + FinalValues.VOLUME)) {
            String message = event.getMessageContent();
            String[] split = message.split(" ");
            int volume = Integer.parseInt(split[1]);
            if (volume >= 1 && volume <= 200) {
                JoinBotCommand.PLAYER.setVolume(volume);
                event.getChannel().sendMessage(String.format("Volume is set to **%d**", volume));
                //debug
                // System.out.println(JoinBotCommand.PLAYER.getVolume());
            } else {
                event.getChannel().sendMessage("Please use a value between 1 and 200");
            }
        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.VOLUME;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Changes musicbots volume");
    }
}
