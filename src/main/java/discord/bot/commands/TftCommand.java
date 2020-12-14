package discord.bot.commands;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.ApiKey;
import discord.bot.commands.finals.BotEmbeds;
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

public class TftCommand implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {
        String message = event.getMessageContent();
        String name = message;
        String[] split = message.split(" ");
        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.TFTCOMMAND)) {
            if (split[1].equalsIgnoreCase("gentlmens"))
                getGentlemens(event);
            else
                printData(event, name.replace(FinalValues.PREFIX + FinalValues.TFTCOMMAND + " ", "").trim());
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.TFTCOMMAND;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Gets the TFT stats for user.");
    }

    public static void getGentlemens(MessageCreateEvent event) {
        printData(event, "LukaLegend007");
        printData(event, "MiqeloS");
        printData(event, "BogTFTa");
        printData(event, "sar der rot");
    }


    private static void printData(MessageCreateEvent event, String s) {
        try {
            if (s.split(" ").length > 1) {
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
            String sN = wins.get("summonerName").getAsString();
            String tier = wins.get("tier").getAsString();
            String rank = wins.get("rank").getAsString();
            String LP = wins.get("leaguePoints").getAsString();
            String w = wins.get("wins").getAsString();
            String l = wins.get("losses").getAsString();
            Float winRate = (Float.parseFloat(w) * 100) / ((Float.parseFloat(l)) + Float.parseFloat(w));

            /*
            String sb = wins.get("summonerName").getAsString() + "\n" +
                    wins.get("tier").getAsString() + "   " + wins.get("rank").getAsString() + "   " + wins.get("leaguePoints").getAsString() + "lp\n" +
                    wins.get("wins").getAsString() + " wins " +
                    Float.parseFloat(wins.get("wins").getAsString()) * 100 / (Float.parseFloat(wins.get("losses").getAsString()) + Float.parseFloat(wins.get("wins").getAsString())) + "% winrate\n";
             */

            EmbedBuilder tftEmbed = BotEmbeds.createTFTEmbed(sN, tier, rank, LP, w, winRate, l);
            event.getChannel().sendMessage(tftEmbed);

        } catch (IOException e) {
            event.getChannel().sendMessage("Player not found");
        }
    }

}
