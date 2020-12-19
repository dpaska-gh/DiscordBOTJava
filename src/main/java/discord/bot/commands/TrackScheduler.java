package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import discord.bot.Main;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.util.concurrent.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    public final BlockingQueue<AudioTrack> queue;
    public static boolean isStarted;

    /**
     * @param player The audio player this scheduler uses
     */
    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
    }

    /**
     * Add the next track to queue or play right away if nothing is in the queue.
     *
     * @param track The track to play or add to queue.
     */
    public void queue(AudioTrack track) {
        // Calling startTrack with the noInterrupt set to true will start the track only if nothing is currently playing. If
        // something is playing, it returns false and does nothing. In that case the player was already playing so this
        // track goes to the queue instead.
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

    /**
     * Start the next track, stopping the current one if it is playing.
     */
    public void nextTrack() {
        // Start the next track, regardless of if something is already playing or not. In case queue was empty, we are
        // giving null to startTrack, which is a valid argument and will simply stop the player.
        player.startTrack(queue.poll(), false);

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {

        isStarted = true;
        System.out.println(isStarted);

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        // Only start the next track if the end reason is suitable for it (FINISHED or LOAD_FAILED)

        if (queue.size() == 0) {
            try {
                isStarted = false;
                System.out.println(isStarted);
                ThreadPool threadPool = Main.api.getThreadPool();
                ScheduledExecutorService a = threadPool.getScheduler();
                a.schedule(new Runnable() {
                    @Override
                    public void run() {

                        if (!isStarted) {
                            JoinBotCommand.server1.getSystemChannel().get().sendMessage(BotEmbeds.musicDisconnectEmbed());
                            JoinBotCommand.audioConnection.close();
                        }

                    }
                }, FinalValues.TIMEOUT, TimeUnit.SECONDS);


            } catch (NullPointerException ignored) {

            }

        } else if (endReason.mayStartNext) {
            nextTrack();
            System.out.println(isStarted);
            isStarted = false;
        }


    }
}
