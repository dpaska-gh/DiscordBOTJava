package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import discord.bot.commands.music.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

public class WoopCommand implements TemplateCommand{

    public static AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    public static AudioPlayer PLAYER = playerManager.createPlayer();
    public static WoopTrackScheduler trackScheduler = new WoopTrackScheduler(PLAYER);
    public static AudioConnection audioConnection;
    public static Server server1;
    public static User user;


    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException {
        Server server = null;

        if (event.getServer().isPresent())
            server = event.getServer().get();

        server1 = server;

        DiscordApi api = Main.api;

        PLAYER.addListener(trackScheduler);

        if (event.getMessage().getContent().contains(FinalValues.PREFIX + FinalValues.WOOP)) {
            ServerVoiceChannel channel;
            //System.out.println("Tu sam");

            if (event.getMessage().getUserAuthor().isPresent())
                user = event.getMessage().getUserAuthor().get();

            if (user.getConnectedVoiceChannel(server).isEmpty()) {
                event.getChannel().sendMessage("You must be connected to a voice channel in order to listen to music!");
            } else {
                //System.out.println("Sad sam tu");
                channel = user.getConnectedVoiceChannel(server).get();

                System.out.println(channel.getName());
                playerManager = new DefaultAudioPlayerManager();
                playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                AudioSource source = new LavaplayerAudioSource(api, PLAYER);
                if (server.getAudioConnection().isEmpty()) {
                    channel.connect().thenAccept(audioConnection -> {
                        audioConnection.setAudioSource(source);
                        WoopCommand.audioConnection = audioConnection;
                    });
                }
                Future<Void> isDone = playerManager.loadItem("https://www.youtube.com/watch?v=-Rnw0D2AdYU", new AudioLoadResultHandler() {
                    @Override
                    public void trackLoaded(AudioTrack track) {
                        PLAYER.playTrack(track);
                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist playlist) {
                        for(AudioTrack audioTrack : playlist.getTracks()){
                            PLAYER.playTrack(audioTrack);
                        }
                    }

                    @Override
                    public void noMatches() {

                    }

                    @Override
                    public void loadFailed(FriendlyException exception) {

                    }

                });


            }

        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.getWOOP();
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Dr. Zoidberg");
    }
}
