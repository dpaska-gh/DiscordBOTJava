package discord.bot.commands;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import discord.bot.ApiKey;
import discord.bot.commands.finals.FinalValues;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FootballCommand implements TemplateCommand {
    public static String[] split;

    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException {
        split = event.getMessageContent().split(" ");
        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.FOOTBALL)) {
            event.getChannel().sendMessage(buildTable());

        }

    }

    /**
     * @return returns EmbedBuilder filled with info that is going to be sent to the channel where the event
     * happened
     * @throws IOException if JSON is empty
     */
    public static EmbedBuilder buildTable() throws IOException {
        OkHttpClient client = new OkHttpClient();
        EmbedBuilder embed = new EmbedBuilder();
        if (split.length == 2) {
            if (split[1].equalsIgnoreCase("england")) {
                builder(client, embed, "2021");
                embed.setThumbnail("https://banner2.cleanpng.com/20180601/plw/kisspng-premier-league-england-national-football-team-live-5b10ebe5a94620.9186064015278356216934.jpg");
                embed.setColor(Color.magenta);
            } else if (split[1].equalsIgnoreCase("italy")) {
                builder(client, embed, "2019");
                embed.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/e/e1/Serie_A_logo_(2019).svg/1200px-Serie_A_logo_(2019).svg.png");
                embed.setColor(Color.BLUE);
            } else if (split[1].equalsIgnoreCase("france")) {
                builder(client, embed, "2015");
                embed.setThumbnail("http://logok.org/wp-content/uploads/2014/11/Ligue_1-logo-.png");
                embed.setColor(Color.gray);
            } else if (split[1].equalsIgnoreCase("spain")) {
                builder(client, embed, "2014");
                embed.setThumbnail("https://logos-download.com/wp-content/uploads/images/Spanish_Primera_Divisi%C3%B3n_La_Liga_logo.png");
                embed.setColor(Color.green);
            } else if (split[1].equalsIgnoreCase("germany")) {
                builder(client, embed, "2002");
                embed.setThumbnail("https://upload.wikimedia.org/wikipedia/en/thumb/d/df/Bundesliga_logo_(2017).svg/1200px-Bundesliga_logo_(2017).svg.png");
                embed.setColor(Color.RED);
            } else {
                embed.setTitle("Currently only arguments ENGLAND, SPAIN, FRANCE, ITALY and GERMANY are supported.")
                        .setColor(Color.RED)
                        .setThumbnail("https://www.pinclipart.com/picdir/middle/346-3467401_crying-sad-emoji-png-whatsapp-new-emoji-2018.png")
                        .setFooter("This command, for now, returns only 1st league of selected country");
            }

        }
        return embed;
    }

    /**
     * @param client Takes HttpClient used to connect to Football API
     * @param embed  Takes the EmbedBuilder on which the info is going to get stored
     * @param id     Takes ID of competition which is supposed to be displayed
     * @throws IOException if HttpClient fails to get a valid JSON
     */

    public static void builder(OkHttpClient client, EmbedBuilder embed, String id) throws IOException {
        Request requestInfo = new Request.Builder().url("https://api.football-data.org/v2/competitions/" + id + "/standings?standingType=TOTAL").header("X-Auth-Token", ApiKey.footballKey).build();
        Response responseInfo = client.newCall(requestInfo).execute();
        Gson gInfo = new Gson();
        JsonObject compInfo = gInfo.fromJson(responseInfo.body().string(), JsonObject.class);
        String name = compInfo.get("competition").getAsJsonObject().get("name").getAsString();
        JsonArray table = compInfo.getAsJsonArray("standings").get(0).getAsJsonObject().get("table").getAsJsonArray();

        for (int i = 0; i < table.size(); i++) {
            JsonObject standingObject = table.get(i).getAsJsonObject();
            String position = standingObject.get("position").getAsString();
            String team = standingObject.get("team").getAsJsonObject().get("name").getAsString();
            String playedGames = standingObject.get("playedGames").getAsString();
            String won = standingObject.get("won").getAsString();
            String draw = standingObject.get("draw").getAsString();
            String lost = standingObject.get("lost").getAsString();
            String points = standingObject.get("points").getAsString();
            String goalsFor = standingObject.get("goalsFor").getAsString();
            String goalsAgainst = standingObject.get("goalsAgainst").getAsString();
            embed.addField(position + ". " + team, won + "W " + draw + "D " + lost + "L " + playedGames + "G " + points + "P " + goalsFor + ":" + goalsAgainst, i % 2 != 0);
        }
        embed.setTitle(name + " standings: ");

    }

    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    @Override
    public String getCommandName() {
        return FinalValues.FOOTBALL;
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Returns league table for selected country");
    }
}
