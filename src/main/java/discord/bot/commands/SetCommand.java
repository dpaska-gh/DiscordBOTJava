package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;

public class SetCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        SortedMap<String, TemplateCommand> commands = Main.setCommands();

        if (event.getMessageContent().contains(FinalValues.PREFIX + FinalValues.SET)) {
            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");
            if (split.length == 3) {
                if (commands.get(FinalValues.getPREFIX() + split[1]) != null) {

                    if (split[1].equalsIgnoreCase(FinalValues.getSET())) {
                        FinalValues.setSET(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getTEMPCHANNEL())) {
                        FinalValues.setTEMPCHANNEL(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getTFTCOMMAND())) {
                        FinalValues.setTFTCOMMAND(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getTRUMPCOMMAND())) {
                        FinalValues.setTRUMPCOMMAND(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getPINGCOMMAND())) {
                        FinalValues.setPINGCOMMAND(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getRANDOMMEME())) {
                        FinalValues.setRANDOMMEME(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getCATFACT())) {
                        FinalValues.setCATFACT(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getCATIMAGE())) {
                        FinalValues.setCATIMAGE(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getRIOTSTATS())) {
                        FinalValues.setRIOTSTATS(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getDELETE())) {
                        FinalValues.setDELETE(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getHELPCOMMAND())) {
                        FinalValues.setHELPCOMMAND(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getPLAY())) {
                        FinalValues.setPLAY(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getODJEBI())) {
                        FinalValues.setODJEBI(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getVOLUME())) {
                        FinalValues.setVOLUME(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getSKIP())) {
                        FinalValues.setSKIP(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getQUEUE())) {
                        FinalValues.setQUEUE(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getSILENCECOMMAND())) {
                        FinalValues.setSILENCECOMMAND(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getHI())) {
                        FinalValues.setHI(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getCLEAR())) {
                        FinalValues.setCLEAR(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getCOVID())) {
                        FinalValues.setCOVID(split[2]);
                    } else if (split[1].equalsIgnoreCase(FinalValues.getNASA())) {
                        FinalValues.setNASA(split[2]);
                    }
                    event.getChannel().sendMessage(split[1].toUpperCase(Locale.ROOT) + " changed to " + split[2]);
                } else if (split[1].equalsIgnoreCase(FinalValues.getTIMEOUTCALL())) {
                    FinalValues.setTIMEOUT(Integer.parseInt(split[2]));
                    event.getChannel().sendMessage(split[1].toUpperCase(Locale.ROOT) + " changed to " + split[2]);
                } else if (split[1].equalsIgnoreCase(FinalValues.getPREFIX())) {
                    FinalValues.setPREFIX(split[2]);
                    event.getChannel().sendMessage(split[1].toUpperCase(Locale.ROOT) + " changed to " + split[2]);
                } else event.getChannel().sendMessage("Unavailable command.");
            } else event.getChannel().sendMessage("Too many arguments");

        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.SET;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Sets the aliases.");
    }
}
