package discord.bot;

import discord.bot.commands.apis.TftCommand;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;

import java.time.LocalTime;
import java.util.Date;
import java.util.TimerTask;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class TimedTFT extends TimerTask {
    Date now;
    LocalTime time;
    LocalTime timeToCompareTo1;
    LocalTime timeToCompareTo2;

    @Override
    public void run() {
        Server server;
        if (Main.api.getServerById("774334763653136384").isPresent()) {
            server = Main.api.getServerById("774334763653136384").get();
            ServerTextChannel systemChannel;
            if (server.getSystemChannel().isPresent()) {
                systemChannel = server.getSystemChannel().get();
                now = new Date();
                time = LocalTime.now();
                timeToCompareTo1 = LocalTime.MIDNIGHT.plus(20, HOURS).plus(0, MINUTES);
                timeToCompareTo2 = LocalTime.MIDNIGHT.plus(21, HOURS).plus(0, MINUTES);
                if (time.compareTo(timeToCompareTo1) > 0 && time.compareTo(timeToCompareTo2) < 0) {
                    try {
                        TftCommand.printData(systemChannel, "LukaLegend007");
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        TftCommand.printData(systemChannel, "MiqeloS");
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        TftCommand.printData(systemChannel, "sar der rot");
                    } catch (NullPointerException ignored) {
                    }
                    try {
                        TftCommand.printData(systemChannel, "BogTFTa");
                    } catch (NullPointerException ignored) {
                    }
                }
            }
        }
    }
}
