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
import java.util.concurrent.atomic.AtomicReference;

public class TftCommand implements TemplateCommand {

    @Override
    public void executeCommand(MessageCreateEvent event) {

        String message = event.getMessageContent();
        String[] split = message.split(" ");
        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.TFTCOMMAND)) {
            if (split[1].equalsIgnoreCase("gentlmens"))
                getGentlemens(event);
            else
                printData(event, message.replace(FinalValues.PREFIX + FinalValues.TFTCOMMAND + " ", "").trim());
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

    /**
     * @param event Event event equals to the event the listener is setup for (here, MessageCreate)
     * @param s     String s equals to the summoner name of the user we are searching for.
     */
    private static void printData(MessageCreateEvent event, String s) {
        try {
            if (s.split(" ").length > 1) {
                s = String.join("%20", s.split(" "));
            }
/*
           this opens the url to get summoner name.
           summoner name, puuid related json parsing
 */
            URL summonerName = new URL
                    ("https://eun1.api.riotgames.com/tft/summoner/v1/summoners/by-name/" + s + "?api_key=" + ApiKey.riotApiKey);
            URLConnection name = summonerName.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(name.getInputStream()));
            JsonObject jsonObject = (JsonObject) JsonParser.parseReader(in);
            JsonElement id = jsonObject.get("id");
            JsonElement puuid = jsonObject.get("puuid");
            JsonElement profileIconId = jsonObject.get("profileIconId");


            //this url gets lp and other useless facts
            URL url = new URL
                    ("https://eun1.api.riotgames.com/tft/league/v1/entries/by-summoner/" + id.toString().replace("\"", "") + "?api_key=" + ApiKey.riotApiKey);

            //this gets lp and info about divisions (opens connection of above mentioned url)
            URLConnection request = url.openConnection();
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(request.getInputStream()));
            JsonArray array = JsonParser.parseReader(inputStream).getAsJsonArray();
            JsonElement winsElement = array.get(0);
            JsonObject wins = winsElement.getAsJsonObject();

            //this url opens the last match the player played
            URL puuidmatches = new URL("https://europe.api.riotgames.com/tft/match/v1/matches/by-puuid/" + puuid.toString().replace("\"", "") + "/ids?count=1&api_key=" + ApiKey.riotApiKey);
            URLConnection puuidMatches = puuidmatches.openConnection();
            BufferedReader puuidMatchesReader = new BufferedReader(new InputStreamReader(puuidMatches.getInputStream()));
            JsonArray arraypuuid = JsonParser.parseReader(puuidMatchesReader).getAsJsonArray();

            //gets last match as string from array
            String lastMatch = arraypuuid.get(0).getAsString();
            //System.out.println(lastMatch);
            //opens url to last match info
            URL lastMatchPlacement = new URL("https://europe.api.riotgames.com/tft/match/v1/matches/" + lastMatch.replace("\"", "") + "?api_key=" + ApiKey.riotApiKey);
            URLConnection lastMatchPlacementConnection = lastMatchPlacement.openConnection();
            //System.out.println(lastMatchPlacementConnection.toString());
            BufferedReader lastMatchPlacementReader = new BufferedReader(new InputStreamReader(lastMatchPlacementConnection.getInputStream()));

            //gets info about participants
            JsonObject info = JsonParser.parseReader(lastMatchPlacementReader).getAsJsonObject();
            JsonObject infoObject = info.getAsJsonObject("info");
            JsonArray participants = infoObject.getAsJsonArray("participants");
            //System.out.println(participants.toString());

            //atomic because you cant use normal object in foreach (just a json object but ATOMIC)
            AtomicReference<JsonObject> a = new AtomicReference<>();

            //loops through JsonArray and finds matching puuid for user
            participants.iterator().forEachRemaining(elements -> {
                if (elements.getAsJsonObject().get("puuid").toString().equals(puuid.toString())) {
                    a.getAndSet(elements.getAsJsonObject());
                }
                //System.out.println(elements.getAsJsonObject().get("puuid").getAsString());
            });

            //get jsonobject from atomic jsonobject
            JsonObject aObject = a.get();



            //System.out.println(request.toString());
            String sN = wins.get("summonerName").getAsString();
            String tier = wins.get("tier").getAsString();
            String rank = wins.get("rank").getAsString();
            String LP = wins.get("leaguePoints").getAsString();
            String w = wins.get("wins").getAsString();
            String l = wins.get("losses").getAsString();
            String placement = aObject.get("placement").getAsString();
            String profileIconIds = profileIconId.toString();
            Float winRate = (Float.parseFloat(w) * 100) / ((Float.parseFloat(l)) + Float.parseFloat(w));
            //System.out.println(profileIconIds);
            /*
            String sb = wins.get("summonerName").getAsString() + "\n" +
                    wins.get("tier").getAsString() + "   " + wins.get("rank").getAsString() + "   " + wins.get("leaguePoints").getAsString() + "lp\n" +
                    wins.get("wins").getAsString() + " wins " +
                    Float.parseFloat(wins.get("wins").getAsString()) * 100 / (Float.parseFloat(wins.get("losses").getAsString()) + Float.parseFloat(wins.get("wins").getAsString())) + "% winrate\n";
             */

            EmbedBuilder tftEmbed = BotEmbeds.createTFTEmbed(profileIconIds, placement, sN, tier, rank, LP, w, winRate, l);
            event.getChannel().sendMessage(tftEmbed);

        } catch (IOException e) {
            event.getChannel().sendMessage("Player not found");
        }
    }

}
