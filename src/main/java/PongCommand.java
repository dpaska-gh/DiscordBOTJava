public class PongCommand extends Main {

    public void pongCommand(){
        Main.api.addMessageCreateListener(event -> {
            if(event.getMessageContent().equalsIgnoreCase("!ping")) {
                event.getChannel().sendMessage("Pong!");
            }
        });
    }
}
