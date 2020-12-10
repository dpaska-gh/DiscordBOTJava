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
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;

import java.awt.*;


public class JoinBotCommand extends Main {

    public static void joinChannel() {
        api.addMessageCreateListener(event -> {
            Server server = event.getServer().get();

            if (event.getMessage().getContent().equalsIgnoreCase("krava mi je vusla")) {
                ServerVoiceChannel channel;

                channel = event.getMessage().getAuthor().asUser().get().getConnectedVoiceChannel(server).get();
                System.out.println(channel.getName());

                channel.connect().thenAccept(audioConnection -> {
                    AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
                    playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                    AudioPlayer player = playerManager.createPlayer();
                    AudioSource source = new LavaplayerAudioSource(api, player);
                    audioConnection.setAudioSource(source);
                    playerManager.loadItem("https://www.youtube.com/watch?v=7Fk1tjPn_sc", new AudioLoadResultHandler() {
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

                        }
                    });
                }).exceptionally(e -> {
                    // Failed to connect to voice channel (no permissions?)
                    e.printStackTrace();
                    return null;
                });

                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Now playing")
                        .setDescription("Zadruga krava mi je v detelju vusla")
                        .setColor(Color.BLUE);
                event.getChannel().sendMessage(embed);
            }

            if (event.getMessage().getContent().equalsIgnoreCase("odjebi")) {
                event.getChannel().sendMessage("Odjebi ti.");
                server.getAudioConnection().get().close();

            }


        });
    }
}
