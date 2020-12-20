package discord.bot.commands;

import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) {
        SortedMap<String, TemplateCommand> commands = Main.setCommands();

        if (event.getMessageContent().contains(FinalValues.PREFIX + FinalValues.SET)) {
            Pattern pattern = Pattern.compile("([!-/]|[;-?]|[{-~])");

            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");
            if (split.length == 3) {
                if ((commands.get(FinalValues.getPREFIX() + split[1]) != null) &&
                        (!commands.containsKey(FinalValues.PREFIX + split[2])) &&
                        (!split[2].equalsIgnoreCase(FinalValues.PREFIX)) &&
                        !(split[2].startsWith("http://") ||
                                split[2].startsWith("https://") ||
                                split[2].startsWith("ftp://") ||
                                split[2].startsWith("<") ||
                                split[2].startsWith("@"))) {

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
                    } /*else if (split[1].equalsIgnoreCase(FinalValues.getRIOTSTATS())) {
                        FinalValues.setRIOTSTATS(split[2]);
                    }*/ else if (split[1].equalsIgnoreCase(FinalValues.getDELETE())) {
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
                    if (Integer.parseInt(split[2]) > 1) {
                        FinalValues.setTIMEOUT(Integer.parseInt(split[2]));
                        event.getChannel().sendMessage(split[1].toUpperCase(Locale.ROOT) + " changed to " + split[2]);
                    } else {
                        event.getChannel().sendMessage(FinalValues.getTIMEOUTCALL().toUpperCase() + " should be greater than 1 second.");
                    }
                } else if (split[1].equalsIgnoreCase(FinalValues.getPREFIX())) {
                    char[] prefixChar = split[2].toCharArray();
                    Matcher match = pattern.matcher(prefixChar[0] + "");
                    if (prefixChar.length == 1 && match.find()) {
                        if (prefixChar[0] == FinalValues.getPREFIX().charAt(0)) {
                            event.getChannel().sendMessage("You're trying to set the current prefix to the same prefix??");
                        } else {
                            FinalValues.setPREFIX(String.valueOf(prefixChar[0]));
                            event.getChannel().sendMessage(split[1].toUpperCase(Locale.ROOT) + " changed to " + split[2]);
                        }
                    } else {
                        event.getChannel().sendMessage("Prefix can be only one symbol and cannot be alphabetic/number.");
                    }
                } else
                    event.getChannel().sendMessage("Unavailable command or you're trying to set the command to another command/URL");
            } else event.getChannel().sendMessage("Too many arguments");

        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.SET;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Sets the aliases of commands and changes bot by calling timeout as 1st argument");
    }
}
