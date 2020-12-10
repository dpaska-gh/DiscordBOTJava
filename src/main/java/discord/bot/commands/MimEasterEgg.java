package discord.bot.commands;

import discord.bot.Main;

class MimiEasterEgg extends Main {

    public void mimiEasterEgg() {
        api.addMessageCreateListener(event -> {
            if (event.getMessage().getAuthor().getIdAsString().equals("220551304244625408")) {
                event.getChannel().sendMessage("Mimi je peder");
            }
        });
    }
}
