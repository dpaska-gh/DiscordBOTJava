package discord.bot.commands.finals;

import discord.bot.commands.*;
import discord.bot.commands.musicquiz.MusicQuizCall;

import java.util.SortedMap;
import java.util.TreeMap;

public class CommandsMap {
    /**
     * Fills new commands to a map for easier execution
     *
     * @return commands stored in a map
     */
    public static SortedMap<String, TemplateCommand> setCommands() {
        SortedMap<String, TemplateCommand> commands = new TreeMap<>();
        commands.put(FinalValues.getPREFIX() + FinalValues.CATFACT, new CatFactCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.CATIMAGE, new CatImage());
        commands.put(FinalValues.getPREFIX() + FinalValues.DELETE, new DeleteMessages());
        commands.put(FinalValues.getPREFIX() + FinalValues.HELPCOMMAND, new HelpCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.PINGCOMMAND, new PongCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.RANDOMMEME, new RadnomMeme());
        commands.put(FinalValues.getPREFIX() + FinalValues.SILENCECOMMAND, new SilenceCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.TEMPCHANNEL, new TempChannel());
        commands.put(FinalValues.getPREFIX() + FinalValues.TFTCOMMAND, new TftCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.TRUMPCOMMAND, new TrumpQuoteCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.PLAY, new JoinBotCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.ODJEBI, new DisconnectCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.VOLUME, new VolumeCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.SKIP, new SkipCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.QUEUE, new QueueCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.SET, new SetCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.HI, new HiCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.CLEAR, new ClearCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.COVID, new CovidCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.NASA, new NasaPlanetCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.LICHESS, new LichessCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.FOOTBALL, new FootballCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.REMOVE, new RemoveCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.PAUSE, new PauseCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.RESUME, new ResumeCommand());
        commands.put(FinalValues.getPREFIX() + FinalValues.MUSICQUIZ, new MusicQuizCall());
        commands.put(FinalValues.getPREFIX() + FinalValues.BOOST, new BassBoostCommand());

        return commands;
    }
}
