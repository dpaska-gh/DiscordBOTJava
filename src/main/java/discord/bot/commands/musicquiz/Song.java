package discord.bot.commands.musicquiz;

public class Song {
    public String url;
    public String title;
    public String singer;

    public Song() {

    }

    public Song(String url, String title, String singer) {
        this.url = url;
        this.title = title;
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
