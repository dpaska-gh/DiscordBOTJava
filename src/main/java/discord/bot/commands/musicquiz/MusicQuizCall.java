package discord.bot.commands.musicquiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import discord.bot.LavaplayerAudioSource;
import discord.bot.Main;
import discord.bot.commands.JoinBotCommand;
import discord.bot.commands.TemplateCommand;
import discord.bot.commands.TrackScheduler;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class MusicQuizCall implements TemplateCommand {
    private static AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    private static final AudioPlayer PLAYER = playerManager.createPlayer();
    private static final TrackScheduler trackScheduler = new TrackScheduler(PLAYER);
    private static AudioConnection audioConnection;

    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException {
        ServerVoiceChannel channel = null;

        Server server = event.getServer().get();
        if (event.getMessageContent().equalsIgnoreCase(FinalValues.PREFIX + FinalValues.MUSICQUIZ)) {
            PLAYER.addListener(trackScheduler);

            playerManager = new DefaultAudioPlayerManager();
            playerManager.registerSourceManager(new YoutubeAudioSourceManager());

            if (event.getMessageAuthor().getConnectedVoiceChannel().isPresent()) {
                channel = event.getMessageAuthor().getConnectedVoiceChannel().get();
            } else {
                event.getChannel().sendMessage("You have to be connected to a voice channel to use this command.");
            }

            AudioSource source = new LavaplayerAudioSource(Main.api, PLAYER);


            if (server.getAudioConnection().isEmpty() && channel != null) {
                channel.connect().thenAccept(audioConnection -> {
                    audioConnection.setAudioSource(source);
                    JoinBotCommand.audioConnection = audioConnection;
                });

                ClassLoader cl = getClass().getClassLoader();
                URL r = cl.getResource("playlist.json");
                File file = new File(r.getFile());
                BufferedReader reader = new BufferedReader(new FileReader(file));
                Gson gson = new Gson();
                List<Song> songList = gson.fromJson(reader, new TypeToken<List<Song>>() {
                }.getType());

                Song currentSong = songList.get(new Random().nextInt(songList.size()));
                System.out.println(currentSong.getSinger());
                int i = 0;
                playerManager.loadItem(currentSong.getUrl(), new AudioLoadResultHandler() {
                    @Override
                    public void trackLoaded(AudioTrack track) {
                        PLAYER.playTrack(track);
                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist playlist) {
                        System.out.println("playlist?");
                    }

                    @Override
                    public void noMatches() {

                    }

                    @Override
                    public void loadFailed(FriendlyException exception) {

                    }
                });

                Main.api.addMessageCreateListener(event1 -> {
                    if (event1.getMessageContent().equalsIgnoreCase(currentSong.getSinger()) || event1.getMessageContent().equalsIgnoreCase(currentSong.getTitle())) {
                        event1.getChannel().sendMessage(event1.getMessageAuthor().getDisplayName() + " guessed the song.");

                    }
                });

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
        return FinalValues.MUSICQUIZ;
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Starts music quiz");
    }
}
