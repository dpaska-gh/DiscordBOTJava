package discord.bot.commands.finals;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import discord.bot.commands.TemplateCommand;
import discord.bot.commands.music.JoinBotCommand;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.msgpack.core.annotations.Nullable;

import java.awt.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BotEmbeds {

    public static EmbedBuilder createMusicEmbed(AudioTrack track) {
        return new EmbedBuilder()
                .setTitle("Added to queue")
                .setDescription(track.getInfo().title)
                .setThumbnail("https://img.youtube.com/vi/" + track.getInfo().identifier + "/0.jpg")
                .addField("URL", track.getInfo().uri)
                .setFooter("Requested by: " + JoinBotCommand.user.getName());
    }

    public static EmbedBuilder factorEmbed(JsonObject info) {
        JsonArray factors = info.getAsJsonArray("factors");
        return new EmbedBuilder()
                .setTitle("Showing factors of number: " + info.get("id").getAsString().replace("\"", ""));
    }

    public static EmbedBuilder createTFTEmbed(String profileIconId, String placement, String summonerName, String tier, String rank, String leaguePoints, String wins, Float wr, String losses) {
        String version = "10.25.1";
        String extension;
        EmbedBuilder embed = new EmbedBuilder();
        switch (Integer.parseInt(placement.substring(placement.length() - 1))) {
            case 1:
                extension = "st";
                break;
            case 2:
                extension = "nd";
                break;
            case 3:
                extension = "rd";
                break;
            default:
                extension = "th";
                break;
        }
        embed.setTitle("**TFT Profile of:** " + summonerName)
                .addField("Tier", tier + " " + rank + " - " + leaguePoints + "LP")
                .addField("Losses", losses)
                .addField("Wins", wins)
                .addField("Winrate", wr.toString() + "%")
                .addField("Last game he played, " + summonerName + " placed", placement + extension)
                .setThumbnail("https://ddragon.leagueoflegends.com/cdn/" + version + "/img/profileicon/" + profileIconId + ".png");

        if (summonerName.equalsIgnoreCase("TFTLegend007") || summonerName.equalsIgnoreCase("LukaLegend007"))
            embed.setFooter("Luka was master btw, no joke.");

        return embed;
    }

    public static EmbedBuilder musicQueueEmbed(BlockingQueue<AudioTrack> queue) {

        EmbedBuilder embedBuilder = new EmbedBuilder();

        AtomicInteger i = new AtomicInteger();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(("**Now playing: %s** \n"), JoinBotCommand.PLAYER.getPlayingTrack().getInfo().title));
        queue.forEach(elements -> {
            i.getAndIncrement();
            stringBuilder.append(String.format("**%s.** %s \n", i, elements.getInfo().title));
        });

        return embedBuilder.setTitle("Current music queue: ")
                .setDescription(stringBuilder.toString());

    }

    public static EmbedBuilder musicDisconnectEmbed() {
        return new EmbedBuilder().setTitle("Disconnected because I had no music and was in channel for too long.");
    }

    public static EmbedBuilder tempEmbed(String channelName) {
        return new EmbedBuilder().setTitle("Created temporary channel")
                .setDescription("With the name: " + channelName);
    }

    public static EmbedBuilder trumpEmbed(String quote) {
        return new EmbedBuilder()
                .setTitle("Trump said:")
                .setDescription(quote);
    }

    public static EmbedBuilder catFactEmbed(String catFact) {
        return new EmbedBuilder()
                .setTitle("Well known cat fact:")
                .setDescription(catFact);
    }

    public static EmbedBuilder skipMusicEmbed(AudioTrack track) {
        return new EmbedBuilder()
                .setTitle("Now playing:")
                .setDescription(track.getInfo().title)
                .setThumbnail("https://img.youtube.com/vi/" + track.getInfo().identifier + "/0.jpg")
                .addField("URL", track.getInfo().uri);
    }

    public static EmbedBuilder helpEmbed(boolean samohelp, @Nullable String command) {
        SortedMap<String, TemplateCommand> commands = CommandsMap.setCommands();
        if (samohelp) {
            EmbedBuilder helpEmbed = new EmbedBuilder().setTitle("List of all available commands:")
                    .addField("Current bot timeout: ", FinalValues.TIMEOUT.toString())
                    .addField("Current prefix: ", FinalValues.PREFIX);
            for (Map.Entry<String, TemplateCommand> commandName : commands.entrySet()) {
                helpEmbed.addInlineField(commandName.getKey(), commandName.getValue().getCommandDescription().toString().replace("[", "\"").replace("]", "\""));
            }
            return helpEmbed;
        } else {
            EmbedBuilder helpEmbed = new EmbedBuilder().setTitle("Help command: " + command);
            if (commands.containsKey(command)) {
                helpEmbed.addField(commands.get(command).getCommandName(), commands.get(command).getCommandDescription().toString().replace("[", "").replace("]", ""));
                System.out.println(command);
                return helpEmbed;
            } else if (commands.containsKey(FinalValues.PREFIX + command)) {
                System.out.println(command);
                helpEmbed.setTitle("Help command: " + FinalValues.PREFIX + command);
                StringBuilder sb = new StringBuilder(command);
                sb.insert(0, FinalValues.getPREFIX());
                command = sb.toString();
                helpEmbed.addField(commands.get(command).getCommandName(), commands.get(command).getCommandDescription().toString().replace("[", "").replace("]", ""));
                return helpEmbed;
            } else {
                helpEmbed.setDescription("Unknown command");
                return helpEmbed;
            }

        }

    }

    public static EmbedBuilder lichessEmbed(String username,
                                            String bulletRating,
                                            String puzzleRating,
                                            String rapidRating,
                                            String classicalRating,
                                            String blitzRating,
                                            String bulletGames,
                                            String puzzleGames,
                                            String rapidGames,
                                            String classicalGames,
                                            String blitzGames,
                                            Boolean onlineStatus,
                                            boolean bulletProv,
                                            boolean puzzleProv,
                                            boolean rapidProv,
                                            boolean classicalProv,
                                            boolean blitzProv,
                                            String timePlayed,
                                            Long lastSeen) {

        EmbedBuilder lichessEmbed = new EmbedBuilder().setTitle("Lichess.org stats for user: " + username).setThumbnail("https://upload.wikimedia.org/wikipedia/commons/thumb/a/af/Lichess_Logo.svg/250px-Lichess_Logo.svg.png");
        if (bulletProv)
            lichessEmbed.addField("BULLET rating over " + bulletGames + " games.", bulletRating + "?");
        else
            lichessEmbed.addField("BULLET rating over " + bulletGames + " games.", bulletRating);
        if (puzzleProv)
            lichessEmbed.addField("PUZZLE rating over " + puzzleGames + " games.", puzzleRating + "?");
        else
            lichessEmbed.addField("PUZZLE rating over " + puzzleGames + " games.", puzzleRating);
        if (rapidProv)
            lichessEmbed.addField("RAPID rating over " + rapidGames + " games.", rapidRating + "?");
        else
            lichessEmbed.addField("RAPID rating over " + rapidGames + " games.", rapidRating);
        if (classicalProv)
            lichessEmbed.addField("CLASSICAL rating over " + classicalGames + " games.", classicalRating + "?");
        else
            lichessEmbed.addField("CLASSICAL rating over " + classicalGames + " games.", classicalRating);
        if (blitzProv)
            lichessEmbed.addField("BLITZ rating over " + blitzGames + " games.", blitzRating + "?");
        else
            lichessEmbed.addField("BLITZ rating over " + blitzGames + " games.", blitzRating);

        lichessEmbed.setDescription("**Total time played:** " + timePlayed);

        if (onlineStatus) {
            lichessEmbed.setFooter("User is currently ONLINE");
        } else {
            Date lastSeenDate = new Date(lastSeen);
            var dateToStr = DateFormat.getDateTimeInstance().format(lastSeenDate);
            lichessEmbed.setFooter("Last seen on: " + dateToStr);
        }

        lichessEmbed.setColor(Color.LIGHT_GRAY);

        return lichessEmbed;
    }

}

