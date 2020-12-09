import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class catFactCommand extends Main {

        public void getCatFact() {
                Main.api.addMessageCreateListener(event -> {
                if(event.getMessageContent().equalsIgnoreCase("!catfact")) {
                    try {
                        URL loginurl = new URL("https://catfact.ninja/fact");
                        URLConnection yc = loginurl.openConnection();
                        yc.setConnectTimeout(10 * 1000);
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        yc.getInputStream()));
                        String inputLine = in.readLine();
                        JsonParser parser = new JsonParser();
                        JsonObject array = parser.parse(inputLine).getAsJsonObject();
                        event.getChannel().sendMessage(array.get("fact").getAsString());
                    }
                    catch (Exception e){
                        System.out.println(e);
                    }
                }
            });

}

}
