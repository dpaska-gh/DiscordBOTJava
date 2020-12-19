package discord.bot.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.ApiKey;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class NasaPlanetCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.NASA)) {
            try {
                URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=" + ApiKey.nasaKey);
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input = in.readLine();
                JsonObject object = JsonParser.parseString(input).getAsJsonObject();
                event.getChannel().sendMessage(object.get("url").getAsString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.PREFIX + FinalValues.NASA;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Returns NASA's planet of the day image");
    }
}
