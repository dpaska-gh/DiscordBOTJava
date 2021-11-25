package discord.bot.commands.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.FunctionalResultHandler;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import discord.bot.Main;
import discord.bot.commands.TemplateCommand;
import discord.bot.commands.finals.BotEmbeds;
import discord.bot.commands.finals.FinalValues;
import discord.bot.commands.finals.GetYoutubeURL;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Collections;
import java.util.List;


public class JoinBotCommand implements TemplateCommand {
    public static AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    public static AudioPlayer PLAYER = playerManager.createPlayer();
    public static TrackScheduler trackScheduler = new TrackScheduler(PLAYER);
    public static AudioConnection audioConnection;
    public static Server server1;
    public static User user;

    @Override
    public void executeCommand(MessageCreateEvent event) {
        Server server = null;

        if (event.getServer().isPresent())
            server = event.getServer().get();

        server1 = server;

        DiscordApi api = Main.api;

        PLAYER.addListener(trackScheduler);

        if (event.getMessage().getContent().contains(FinalValues.PREFIX + FinalValues.PLAY)) {
            ServerVoiceChannel channel;

            if (event.getMessage().getUserAuthor().isPresent())
                user = event.getMessage().getUserAuthor().get();

            if (user.getConnectedVoiceChannel(server).isEmpty()) {
                event.getChannel().sendMessage("You must be connected to a voice channel in order to listen to music!");
            } else {

                channel = user.getConnectedVoiceChannel(server).get();

                //System.out.println(channel.getName());
                playerManager = new DefaultAudioPlayerManager();
                playerManager.registerSourceManager(new YoutubeAudioSourceManager());
                GetYoutubeURL getURL = new GetYoutubeURL();

                AudioSource source = new LavaplayerAudioSource(api, PLAYER);

                if (server.getAudioConnection().isEmpty()) {
                    channel.connect().thenAccept(audioConnection -> {
                        audioConnection.setAudioSource(source);
                        JoinBotCommand.audioConnection = audioConnection;
                    });
                }

                playerManager.loadItem("ytsearch: " + getURL.getResult(event), new FunctionalResultHandler(null, audioPlaylist -> {
                    trackScheduler.queue(audioPlaylist.getTracks().get(0));
                    event.getChannel().sendMessage(BotEmbeds.createMusicEmbed(audioPlaylist.getTracks().get(0)));
                }, null, null));

            }
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

