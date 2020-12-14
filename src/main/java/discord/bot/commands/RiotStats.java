package discord.bot.commands;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.Champions;
import com.merakianalytics.orianna.types.core.staticdata.ProfileIcon;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import discord.bot.ApiKey;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;

public class RiotStats implements TemplateCommand {
    public static ProfileIcon profileIcon;
    @Override
    public void executeCommand(MessageCreateEvent event) {
        String messageContent = event.getMessageContent();
        String[] split = messageContent.split(" ");

        if (split[0].equalsIgnoreCase(FinalValues.PREFIX + FinalValues.RIOTSTATS)) {
            Orianna.setRiotAPIKey(ApiKey.riotApiKey);
            Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST);
            Summoner summoner = Orianna.summonerNamed(split[1]).get();
            profileIcon = summoner.getProfileIcon();
            event.getChannel().sendMessage(summoner.getName() + " is level " + summoner.getLevel() + " on the " + summoner.getRegion() + " server.");
            Champions champions = Orianna.getChampions();
            Champion randomChampion = champions.get((int) (Math.random() * champions.size()));
            ProfileIcon profileIcon = summoner.getProfileIcon();
            event.getChannel().sendMessage("He enjoys playing champions such as " + randomChampion.getName());

            event.getChannel().sendMessage("He's not as good as <@189090587788443648> at League, but probably a better Java programmer!");
        }
    }

    @Override
    public String getCommandName() {
        return FinalValues.RIOTSTATS;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Returns the stats for riot account.");
    }
}
