package discord.bot;

import discord.bot.commands.TftCommand;
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

    @Override
    public void run() {
        Server server = Main.api.getServerById("774334763653136384").get();
        ServerTextChannel systemChannel = server.getSystemChannel().get();
        now = new Date();
        time = LocalTime.now();
        if (time.compareTo(LocalTime.MIDNIGHT.plus(20, HOURS).plus(0, MINUTES)) > 0 && time.compareTo(LocalTime.MIDNIGHT.plus(21, HOURS).plus(0, MINUTES)) < 0) {
            TftCommand.printData(systemChannel, "LukaLegend007");
            TftCommand.printData(systemChannel, "MiqeloS");
            TftCommand.printData(systemChannel, "BogTFTa");
            TftCommand.printData(systemChannel, "sar der rot");
        }
    }
}
