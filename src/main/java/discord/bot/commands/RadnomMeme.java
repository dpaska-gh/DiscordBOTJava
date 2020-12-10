package discord.bot.commands;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import discord.bot.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RadnomMeme {

   public void  randomMeme() {

       Main.api.addMessageCreateListener(event -> {
           if (event.getMessageContent().equalsIgnoreCase("!meme")) {
               try {
                   URL loginurl = new URL("https://meme-api.herokuapp.com/gimme");
                   URLConnection yc = loginurl.openConnection();
                   yc.setConnectTimeout(10 * 1000);
                   BufferedReader in = new BufferedReader(
                           new InputStreamReader(
                                   yc.getInputStream()));
                   String inputLine = in.readLine();
                   JsonParser parser = new JsonParser();
                   JsonObject array = parser.parse(inputLine).getAsJsonObject();
                   event.getChannel().sendMessage(array.get("url").getAsString());
                   event.addReactionsToMessage("️👌");
               } catch (Exception e) {
                   System.out.println(e);
               }
           }
       });
    }

}
