package discord.bot.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class CatImage implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {


        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.CATIMAGE)) {
            try {
                URL loginurl = new URL("https://api.thecatapi.com/v1/images/search");
                URLConnection yc = loginurl.openConnection();
                yc.setConnectTimeout(10 * 1000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                yc.getInputStream()));
                String inputLine = in.readLine();

                JsonArray array = JsonParser.parseString(inputLine).getAsJsonArray();
                JsonElement test1 = array.get(0);
                JsonObject test2 = test1.getAsJsonObject();
                event.getChannel().sendMessage(test2.get("url").getAsString());
                event.addReactionsToMessage("❤️");
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }

    @Override
    public String getCommandName() {
        return FinalValues.CATIMAGE;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Gives random cat image.");
    }
}
