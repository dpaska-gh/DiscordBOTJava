package discord.bot.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class CovidCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException {
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.COVID)) {
            URL loginUrl = new URL("https://www.koronavirus.hr/json/?action=po_danima_zupanijama_zadnji");
            URLConnection yc = loginUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            JsonArray array = JsonParser.parseString(inputLine).getAsJsonArray();
            JsonElement element = array.get(0);
            JsonObject object = element.getAsJsonObject();
            JsonArray podaciDetaljno = object.get("PodaciDetaljno").getAsJsonArray();
            embedBuilder.setTitle("**Ukupan broj zarazenih po zupanijama: **");
            podaciDetaljno.forEach(podaci -> {
                embedBuilder.addInlineField(podaci.getAsJsonObject().get("Zupanija").getAsString(), podaci.getAsJsonObject().get("broj_zarazenih").getAsString());
            });
            event.getChannel().sendMessage(embedBuilder);
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.PREFIX + FinalValues.COVID;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Current covid stats...");
    }
}
