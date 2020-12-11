package discord.bot.commands;

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
import discord.bot.commands.finals.FinalValues;
import discord.bot.commands.finals.GetYoutubeURL;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;


public class JoinBotCommand implements TemplateCommand {


    @Override
    public void executeCommand(MessageCreateEvent event) {
        Server server = event.getServer().get();
        DiscordApi api = Main.api;
        GetYoutubeURL urlGetter = new GetYoutubeURL();
        if (event.getMessage().getContent().contains(FinalValues.PREFIX + FinalValues.PLAY)) {
            ServerVoiceChannel channel;

            channel = event.getMessage().getAuthor().asUser().get().getConnectedVoiceChannel(server).get();
            System.out.println(channel.getName());

            channel.connect().thenAccept(audioConnection -> {
                AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                AudioPlayer player = playerManager.createPlayer();
                AudioSource source = new LavaplayerAudioSource(api, player);
                audioConnection.setAudioSource(source);
                playerManager.loadItem(urlGetter.getURL(event), new AudioLoadResultHandler() {
                    @Override
                    public void trackLoaded(AudioTrack track) {
                        player.playTrack(track);
                    }

                    @Override
                    public void playlistLoaded(AudioPlaylist playlist) {
                        for (AudioTrack track : playlist.getTracks()) {
                            player.playTrack(track);
                        }
                    }

                    @Override
                    public void noMatches() {

                    }

                    @Override
                    public void loadFailed(FriendlyException exception) {
                        event.getChannel().sendMessage("Failed to load");

                    }
                });
            }).exceptionally(e -> {

                e.printStackTrace();
                return null;
            });


        }

    }

    @Override
    public String getCommandName() {
        return FinalValues.PLAY;
    }

    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("Joins the Musicbot.");
    }
}
