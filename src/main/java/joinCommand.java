import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.audio.AudioSource;
import org.javacord.api.entity.DiscordClient;
import org.javacord.api.entity.DiscordEntity;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.Permissionable;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.channel.ChannelCategory;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.server.invite.RichInvite;
import org.javacord.api.entity.user.User;
import org.javacord.api.entity.user.UserStatus;
import org.javacord.api.listener.ObjectAttachableListener;
import org.javacord.api.listener.channel.ChannelAttachableListener;
import org.javacord.api.listener.channel.VoiceChannelAttachableListener;
import org.javacord.api.listener.channel.group.GroupChannelChangeNameListener;
import org.javacord.api.listener.channel.group.GroupChannelCreateListener;
import org.javacord.api.listener.channel.group.GroupChannelDeleteListener;
import org.javacord.api.listener.channel.server.*;
import org.javacord.api.listener.channel.server.voice.*;
import org.javacord.api.listener.channel.user.PrivateChannelCreateListener;
import org.javacord.api.listener.channel.user.PrivateChannelDeleteListener;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;
import org.javacord.api.listener.server.VoiceStateUpdateListener;
import org.javacord.api.listener.server.member.ServerMemberBanListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;
import org.javacord.api.listener.server.member.ServerMemberLeaveListener;
import org.javacord.api.listener.server.member.ServerMemberUnbanListener;
import org.javacord.api.listener.server.role.UserRoleAddListener;
import org.javacord.api.listener.server.role.UserRoleRemoveListener;
import org.javacord.api.listener.user.*;
import org.javacord.api.util.event.ListenerManager;

import java.awt.*;
import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public class joinCommand extends Main {

   public void joinChannel() {
      api.addMessageCreateListener(event -> {
          if(event.getMessage().getContent().equalsIgnoreCase("krava mi je vusla")){
              ServerVoiceChannel channel = new ServerVoiceChannel() {
                  @Override
                  public CompletableFuture<AudioConnection> connect() {
                      return null;
                  }

                  @Override
                  public int getBitrate() {
                      return 0;
                  }

                  @Override
                  public Optional<Integer> getUserLimit() {
                      return Optional.empty();
                  }

                  @Override
                  public Collection<Long> getConnectedUserIds() {
                      return null;
                  }

                  @Override
                  public Collection<User> getConnectedUsers() {
                      return null;
                  }

                  @Override
                  public boolean isConnected(long userId) {
                      return false;
                  }

                  @Override
                  public Optional<ChannelCategory> getCategory() {
                      return Optional.empty();
                  }

                  @Override
                  public Server getServer() {
                      return null;
                  }

                  @Override
                  public int getRawPosition() {
                      return 0;
                  }

                  @Override
                  public CompletableFuture<Collection<RichInvite>> getInvites() {
                      return null;
                  }

                  @Override
                  public <T extends Permissionable & DiscordEntity> Permissions getOverwrittenPermissions(T permissionable) {
                      return null;
                  }

                  @Override
                  public Map<Long, Permissions> getOverwrittenUserPermissions() {
                      return null;
                  }

                  @Override
                  public Map<Long, Permissions> getOverwrittenRolePermissions() {
                      return null;
                  }

                  @Override
                  public Permissions getEffectiveOverwrittenPermissions(User user) {
                      return null;
                  }

                  @Override
                  public CompletableFuture<Void> delete(String reason) {
                      return null;
                  }

                  @Override
                  public String getName() {
                      return null;
                  }

                  @Override
                  public DiscordApi getApi() {
                      return null;
                  }

                  @Override
                  public long getId() {
                      return 0;
                  }

                  @Override
                  public ListenerManager<ServerVoiceChannelMemberLeaveListener> addServerVoiceChannelMemberLeaveListener(ServerVoiceChannelMemberLeaveListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerVoiceChannelMemberLeaveListener> getServerVoiceChannelMemberLeaveListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerVoiceChannelChangeUserLimitListener> addServerVoiceChannelChangeUserLimitListener(ServerVoiceChannelChangeUserLimitListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerVoiceChannelChangeUserLimitListener> getServerVoiceChannelChangeUserLimitListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerVoiceChannelChangeBitrateListener> addServerVoiceChannelChangeBitrateListener(ServerVoiceChannelChangeBitrateListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerVoiceChannelChangeBitrateListener> getServerVoiceChannelChangeBitrateListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerVoiceChannelMemberJoinListener> addServerVoiceChannelMemberJoinListener(ServerVoiceChannelMemberJoinListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerVoiceChannelMemberJoinListener> getServerVoiceChannelMemberJoinListeners() {
                      return null;
                  }

                  @Override
                  public <T extends ServerVoiceChannelAttachableListener & ObjectAttachableListener> Collection<ListenerManager<? extends ServerVoiceChannelAttachableListener>> addServerVoiceChannelAttachableListener(T listener) {
                      return null;
                  }

                  @Override
                  public <T extends ServerVoiceChannelAttachableListener & ObjectAttachableListener> void removeServerVoiceChannelAttachableListener(T listener) {

                  }

                  @Override
                  public <T extends ServerVoiceChannelAttachableListener & ObjectAttachableListener> Map<T, List<Class<T>>> getServerVoiceChannelAttachableListeners() {
                      return null;
                  }

                  @Override
                  public <T extends ServerVoiceChannelAttachableListener & ObjectAttachableListener> void removeListener(Class<T> listenerClass, T listener) {

                  }

                  @Override
                  public <T extends VoiceChannelAttachableListener & ObjectAttachableListener> Collection<ListenerManager<? extends VoiceChannelAttachableListener>> addVoiceChannelAttachableListener(T listener) {
                      return null;
                  }

                  @Override
                  public <T extends VoiceChannelAttachableListener & ObjectAttachableListener> void removeVoiceChannelAttachableListener(T listener) {

                  }

                  @Override
                  public <T extends VoiceChannelAttachableListener & ObjectAttachableListener> Map<T, List<Class<T>>> getVoiceChannelAttachableListeners() {
                      return null;
                  }

                  @Override
                  public <T extends VoiceChannelAttachableListener & ObjectAttachableListener> void removeListener(Class<T> listenerClass, T listener) {

                  }

                  @Override
                  public ListenerManager<ServerChannelChangePositionListener> addServerChannelChangePositionListener(ServerChannelChangePositionListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerChannelChangePositionListener> getServerChannelChangePositionListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerChannelChangeOverwrittenPermissionsListener> addServerChannelChangeOverwrittenPermissionsListener(ServerChannelChangeOverwrittenPermissionsListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerChannelChangeOverwrittenPermissionsListener> getServerChannelChangeOverwrittenPermissionsListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerChannelDeleteListener> addServerChannelDeleteListener(ServerChannelDeleteListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerChannelDeleteListener> getServerChannelDeleteListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<ServerChannelChangeNameListener> addServerChannelChangeNameListener(ServerChannelChangeNameListener listener) {
                      return null;
                  }

                  @Override
                  public List<ServerChannelChangeNameListener> getServerChannelChangeNameListeners() {
                      return null;
                  }

                  @Override
                  public ListenerManager<VoiceStateUpdateListener> addVoiceStateUpdateListener(VoiceStateUpdateListener listener) {
                      return null;
                  }

                  @Override
                  public List<VoiceStateUpdateListener> getVoiceStateUpdateListeners() {
                      return null;
                  }

                  @Override
                  public <T extends ServerChannelAttachableListener & ObjectAttachableListener> Collection<ListenerManager<? extends ServerChannelAttachableListener>> addServerChannelAttachableListener(T listener) {
                      return null;
                  }

                  @Override
                  public <T extends ServerChannelAttachableListener & ObjectAttachableListener> void removeServerChannelAttachableListener(T listener) {

                  }

                  @Override
                  public <T extends ServerChannelAttachableListener & ObjectAttachableListener> Map<T, List<Class<T>>> getServerChannelAttachableListeners() {
                      return null;
                  }

                  @Override
                  public <T extends ServerChannelAttachableListener & ObjectAttachableListener> void removeListener(Class<T> listenerClass, T listener) {

                  }

                  @Override
                  public <T extends ChannelAttachableListener & ObjectAttachableListener> Collection<ListenerManager<T>> addChannelAttachableListener(T listener) {
                      return null;
                  }

                  @Override
                  public <T extends ChannelAttachableListener & ObjectAttachableListener> void removeChannelAttachableListener(T listener) {

                  }

                  @Override
                  public <T extends ChannelAttachableListener & ObjectAttachableListener> Map<T, List<Class<T>>> getChannelAttachableListeners() {
                      return null;
                  }

                  @Override
                  public <T extends ChannelAttachableListener & ObjectAttachableListener> void removeListener(Class<T> listenerClass, T listener) {

                  }
              };
              Server server = api.getServerById("774334763653136384").get();
              //channel = api.getServerVoiceChannelById("774367293492822056").get();
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
                        for(AudioTrack track : playlist.getTracks()){
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
        if(event.getMessage().getContent().equalsIgnoreCase("odjebi")){
            event.getChannel().sendMessage("Odjebi ti.");




        }


      });
   }
}
