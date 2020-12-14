package discord.bot.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class CatFactCommand implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {

        {
            if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.CATFACT)) {
                {
                    try {
                        URL loginUrl = new URL("https://catfact.ninja/fact");
                        URLConnection yc = loginUrl.openConnection();
                        yc.setConnectTimeout(10 * 1000);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        yc.getInputStream()));
                        String inputLine = in.readLine();

                        JsonObject array = JsonParser.parseString(inputLine).getAsJsonObject();
                        String catFact = array.get("fact").getAsString();
                        //event.getChannel().sendMessage(array.get("fact").getAsString());
                        event.getChannel().sendMessage(BotEmbeds.catFactEmbed(catFact));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.CATFACT;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Returns the fact about cat");
    }
}
