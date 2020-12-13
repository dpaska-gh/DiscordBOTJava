package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import discord.bot.Main;
import org.javacord.api.DiscordApi;

import static discord.bot.commands.JoinBotCommand.PLAYER;

public class DisconnectOnFinish {
    public static void onFinish() {

        DiscordApi api = Main.api;

        TrackScheduler trackScheduler = JoinBotCommand.trackScheduler;

        trackScheduler.onTrackEnd(PLAYER, PLAYER.getPlayingTrack(), AudioTrackEndReason.FINISHED);


    }

}
