package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import static discord.bot.commands.JoinBotCommand.PLAYER;

public class DisconnectOnFinish {
    public static void onFinish() {

        TrackScheduler trackScheduler = JoinBotCommand.trackScheduler;

        trackScheduler.onTrackEnd(PLAYER, PLAYER.getPlayingTrack(), AudioTrackEndReason.FINISHED);


    }

}
