package discord.bot.commands;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import discord.bot.ApiKey;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LichessCommand implements TemplateCommand {
    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException {
        String[] split = event.getMessageContent().split(" ");
        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.LICHESS)) {
            if (split.length == 2) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://lichess.org/api/user/" + split[1].toLowerCase()).header("Authorization", "Bearer " + ApiKey.lichessKey).build();
                Response response = client.newCall(request).execute();
                Gson g = new Gson();
                JsonObject allInfo = g.fromJson(response.body().string(), JsonObject.class);
                String username = allInfo.get("username").toString();


                JsonObject ratings = allInfo.get("perfs").getAsJsonObject();

                String bulletRating = ratings.get("bullet").getAsJsonObject().get("rating").toString();
                String puzzleRating = ratings.get("puzzle").getAsJsonObject().get("rating").toString();
                String rapidRating = ratings.get("rapid").getAsJsonObject().get("rating").toString();
                String classicalRating = ratings.get("classical").getAsJsonObject().get("rating").toString();
                String blitzRating = ratings.get("blitz").getAsJsonObject().get("rating").toString();


                String bulletGames = ratings.get("bullet").getAsJsonObject().get("games").toString();
                String puzzleGames = ratings.get("puzzle").getAsJsonObject().get("games").toString();
                String rapidGames = ratings.get("rapid").getAsJsonObject().get("games").toString();
                String classicalGames = ratings.get("classical").getAsJsonObject().get("games").toString();
                String blitzGames = ratings.get("blitz").getAsJsonObject().get("games").toString();

                Boolean onlineStatus = allInfo.get("online").getAsBoolean();
                boolean bulletProv = false;
                boolean puzzleProv = false;
                boolean rapidProv = false;
                boolean classicalProv = false;
                boolean blitzProv = false;

                String timePlayed = convertSec(allInfo.get("playTime").getAsJsonObject().get("total").getAsInt());


                try {
                    bulletProv = ratings.get("bullet").getAsJsonObject().get("prov").getAsBoolean();
                } catch (NullPointerException ignored) {
                }
                try {
                    puzzleProv = ratings.get("puzzle").getAsJsonObject().get("prov").getAsBoolean();
                } catch (NullPointerException ignored) {
                }
                try {
                    rapidProv = ratings.get("rapid").getAsJsonObject().get("prov").getAsBoolean();
                } catch (NullPointerException ignored) {
                }
                try {
                    classicalProv = ratings.get("classical").getAsJsonObject().get("prov").getAsBoolean();
                } catch (NullPointerException ignored) {
                }
                try {
                    blitzProv = ratings.get("blitz").getAsJsonObject().get("prov").getAsBoolean();
                } catch (NullPointerException ignored) {
                }
                Long lastSeen = allInfo.get("seenAt").getAsLong();

                event.getChannel().sendMessage(BotEmbeds.lichessEmbed(username, bulletRating,
                        puzzleRating,
                        rapidRating,
                        classicalRating,
                        blitzRating,
                        bulletGames,
                        puzzleGames,
                        rapidGames,
                        classicalGames,
                        blitzGames,
                        onlineStatus,
                        bulletProv,
                        puzzleProv,
                        rapidProv,
                        classicalProv,
                        blitzProv,
                        timePlayed,
                        lastSeen));

            } else {
                event.getChannel().sendMessage("Invalid number of arguments");
            }
        }

    }

    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    @Override
    public String getCommandName() {
        return FinalValues.LICHESS;
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Shows user's Lichess.org stats");
    }

    static String convertSec(Integer n) {
        int day = n / (24 * 3600);

        n = n % (24 * 3600);
        int hour = n / 3600;

        n %= 3600;
        int minutes = n / 60;

        n %= 60;
        int seconds = n;
        if (day > 0 && hour > 0 && minutes > 0 && seconds > 0)
            return day + " " + "days " + hour + " " + "hours " + minutes + " " + "minutes " + seconds + " " + "seconds ";
        else if (day <= 0 && hour > 0 && minutes > 0 && seconds > 0)
            return hour + " " + "hours " + minutes + " " + "minutes " + seconds + " " + "seconds ";

        else if (day <= 0 && hour <= 0 && minutes > 0 && seconds > 0)
            return minutes + " " + "minutes " + seconds + " " + "seconds ";

        else if (day <= 0 && hour <= 0 && minutes <= 0 && seconds > 0)
            return seconds + " " + "seconds ";

        return day + " " + "days " + hour
                + " " + "hours " + minutes + " "
                + "minutes " + seconds + " "
                + "seconds ";
    }
}
