package discord.bot.commands;

import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;
import discord.bot.Main;
import discord.bot.commands.finals.FinalValues;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class BassBoostCommand implements TemplateCommand {
    private final EqualizerFactory equalizer;
    private static final float[] BASS_BOOST = {0.2f, 0.15f, 0.1f, 0.05f, 0.0f, -0.05f, -0.1f, -0.1f, -0.1f, -0.1f, -0.1f,
            -0.1f, -0.1f, -0.1f, -0.1f};
    private static boolean boosted = false;

    public BassBoostCommand() {
        this.equalizer = new EqualizerFactory();
    }

    @Override
    public void executeCommand(MessageCreateEvent event) throws IOException, InterruptedException {
        if (event.getMessageContent().equals(FinalValues.PREFIX + FinalValues.BOOST) && boosted && event.getMessageAuthor().getConnectedVoiceChannel().isPresent() && Main.api.getYourself().isConnected(event.getMessageAuthor().getConnectedVoiceChannel().get())) {

            eqStop();
            boosted = false;
            event.getServerTextChannel().get().sendMessage("Stopped boosting.");
        } else if (event.getMessageContent().equals(FinalValues.PREFIX + FinalValues.BOOST) && !boosted && event.getMessageAuthor().getConnectedVoiceChannel().isPresent() && Main.api.getYourself().isConnected(event.getMessageAuthor().getConnectedVoiceChannel().get())) {
            eqhighbass(0.1f);
            eqSetup();
            eqStart();
            eqhighbass(0.1f);
            boosted = true;
            event.getServerTextChannel().get().sendMessage("Boosted.");
        } else
            event.getChannel().sendMessage("You and the bot have to be connected to a channel.");
    }

    private void eqSetup() {
        JoinBotCommand.playerManager.getConfiguration().setFilterHotSwapEnabled(true);
        JoinBotCommand.PLAYER.setFrameBufferDuration(500);
    }

    private void eqStart() {
        JoinBotCommand.PLAYER.setFilterFactory(equalizer);
    }

    private void eqStop() {
        JoinBotCommand.PLAYER.setFilterFactory(null);
    }

    private void eqhighbass(float diff) {
        for (int i = 0; i < BASS_BOOST.length; i++) {
            equalizer.setGain(i, BASS_BOOST[i] + diff);
        }
    }


    /**
     * Method that returns the name of the given command.
     *
     * @return name of command
     */
    @Override
    public String getCommandName() {
        return FinalValues.BOOST;
    }

    /**
     * Method that returns the description of the given command.
     *
     * @return description of given command
     */
    @Override
    public List<String> getCommandDescription() {
        return Collections.singletonList("WIP");
    }
}
