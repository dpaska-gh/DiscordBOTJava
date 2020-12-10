package discord.bot.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class CatImage {
    public static void getCatImg() {
        Main.api.addMessageCreateListener(event -> {
            for (FinalValues.catImageAlias alias : FinalValues.catImageAlias.values()) {
                if (event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + alias)) {
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
        });

    }
}
