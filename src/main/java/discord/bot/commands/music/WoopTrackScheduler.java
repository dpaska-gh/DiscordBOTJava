package discord.bot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import discord.bot.commands.WoopCommand;

import java.util.concurrent.LinkedBlockingQueue;

public class WoopTrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    String lastTitle;
    public WoopTrackScheduler(AudioPlayer player) {
        this.player = player;
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        lastTitle = track.getInfo().title;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if(lastTitle.equals("Zoidberg: Whoop Whoop Whoop"))
            WoopCommand.audioConnection.close();
    }
}
