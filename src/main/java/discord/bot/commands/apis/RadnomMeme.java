package discord.bot.commands.apis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class RadnomMeme implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.RANDOMMEME)) {

            try {
                URL loginurl = new URL("https://meme-api.herokuapp.com/gimme");
                URLConnection yc = loginurl.openConnection();
                yc.setConnectTimeout(10 * 1000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                yc.getInputStream()));
                String inputLine = in.readLine();
                JsonObject array = JsonParser.parseString(inputLine).getAsJsonObject();
                event.getChannel().sendMessage(array.get("url").getAsString());
                event.addReactionsToMessage("️👌");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.RANDOMMEME;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Returns the random meme");
    }
}
