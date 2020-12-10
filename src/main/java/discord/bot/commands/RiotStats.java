package discord.bot.commands;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.league.League;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import discord.bot.ApiKey;
import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;

public class RiotStats {

    public static void getStats() {
        Main.api.addMessageCreateListener(event -> {

            String messageContent = event.getMessageContent();
            String[] split = messageContent.split(" ");

            if (split[0].equalsIgnoreCase(FinalValues.prefix + FinalValues.riotStats)) {
                Orianna.setRiotAPIKey(ApiKey.riotApiKey);
                Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST);
                Summoner summoner = Orianna.summonerNamed(split[1]).get();

                event.getChannel().sendMessage(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");
                Champions champions = Orianna.getChampions();
                Champion randomChampion = champions.get((int) (Math.random() * champions.size()));
                event.getChannel().sendMessage("He enjoys playing champions such as " + randomChampion.getName());

                League challengerLeague = Orianna.challengerLeagueInQueue(Queue.RANKED_SOLO).get();
                Summoner bestNA = challengerLeague.get(0).getSummoner();
                event.getChannel().sendMessage("He's not as good as <@189090587788443648> at League, but probably a better Java programmer!");
            }
        });
    }
}
