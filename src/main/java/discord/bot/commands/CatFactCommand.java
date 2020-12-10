package discord.bot.commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.commands.finals.FinalValues;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static discord.bot.Main.api;

public class CatFactCommand {

    public static void getCatFact() {
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + FinalValues.catFactAlias.catf)
                    || event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + FinalValues.catFactAlias.kittyf)
                    || event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + FinalValues.catFactAlias.mujcekfakt)
                    || event.getMessageContent().equalsIgnoreCase(FinalValues.prefix + FinalValues.catFactAlias.pusf)) {
                try {
                    URL loginUrl = new URL("https://catfact.ninja/fact");
                    URLConnection yc = loginUrl.openConnection();
                    yc.setConnectTimeout(10 * 1000);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    yc.getInputStream()));
                    String inputLine = in.readLine();

                    JsonObject array = JsonParser.parseString(inputLine).getAsJsonObject();
                    event.getChannel().sendMessage(array.get("fact").getAsString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }

}
