package discord.bot.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.ApiKey;
import discord.bot.Main;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class TftCommand {


    public void getTft() throws IOException {

        Main.api.addMessageCreateListener(event -> {
            String message = event.getMessageContent();
            String name = message;
            String[] split = message.split(" ");
            if (split[0].equalsIgnoreCase("!tft")) {
                if (split[1].equalsIgnoreCase("gentlmens"))
                    getGentlemens(event);
                else
                    printData(event, name.replace("!tft ", "").trim());
            }
        });
    }

    public void getGentlemens(MessageCreateEvent event) {
        Thread newThread = new Thread(() -> {
            printData(event, "LukaLegend007");
            event.getChannel().sendMessage("--------------------------------");
        });
        newThread.start();
        Thread newThread2 = new Thread(() -> {

            printData(event, "MiqeloS");
            event.getChannel().sendMessage("--------------------------------");
        });
        newThread2.start();
        Thread newThread3 = new Thread(() -> {
            printData(event, "BogTFTa");
            event.getChannel().sendMessage("--------------------------------");
        });
        newThread3.start();
        Thread newThread4 = new Thread(() -> {
            printData(event, "sar der rot");
        });
        newThread4.start();

    }


    private void printData(MessageCreateEvent event, String s) {
        try {
            if (s.split(" ").length > 1) {
                System.out.println(s.length());
                s = String.join("%20", s.split(" "));
            }
            URL summonerName = new URL
                    ("https://eun1.api.riotgames.com/tft/summoner/v1/summoners/by-name/" + s + "?api_key=" + ApiKey.riotApiKey);

            URLConnection name = summonerName.openConnection();
            System.out.println(name.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(name.getInputStream()));
            JsonObject jsonObject = (JsonObject) JsonParser.parseReader(in);
            JsonElement id = jsonObject.get("id");


            URL url = new URL
                    ("https://eun1.api.riotgames.com/tft/league/v1/entries/by-summoner/" + id.toString().replace("\"", "") + "?api_key=" + ApiKey.riotApiKey);

            URLConnection request = url.openConnection();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(request.getInputStream()));
            JsonArray array = JsonParser.parseReader(inputStream).getAsJsonArray();

            JsonElement winsElement = array.get(0);
            JsonObject wins = winsElement.getAsJsonObject();

            event.getChannel().sendMessage(wins.get("summonerName").getAsString());
            event.getChannel().sendMessage
                    (wins.get("tier").getAsString() + "   " + wins.get("rank").getAsString() + "   " + wins.get("leaguePoints").getAsString() + "lp");
            event.getChannel().sendMessage
                    (wins.get("wins").getAsString() + " wins " +
                            Float.parseFloat(wins.get("wins").getAsString()) * 100 / (Float.parseFloat(wins.get("losses").getAsString()) + Float.parseFloat(wins.get("wins").getAsString())) + "% winrate");

        } catch (IOException e) {
            event.getChannel().sendMessage("Player not found");
        }
    }


}
