package me.tl0x.message;

import me.tl0x.util.JsonHelper;
import me.tl0x.util.EmbedObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class SelfMessage {

    private final String TOKEN;
    private String content;
    private String channelId;
    private String url;
    private boolean tts;
    private List<EmbedObject> embeds;


    public SelfMessage(String TOKEN) {
        this.TOKEN = TOKEN;
    }

    public String getContent() {
        return content;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public void addEmbed(EmbedObject tba) {
        this.embeds.add(tba);
    }

    public void setChannelId(String id) {
        this.channelId = id;
        this.url = "https://discordapp.com/api/v6/channels/" + id + "/messages";
    }

    public void setChannelId(int id) {
        String strid = Integer.toString(id);
        this.channelId = strid;
        this.url = "https://discordapp.com/api/v6/channels/" + strid + "/messages";
    }

    public void sendMessage() throws IOException {
        JsonHelper payload = new JsonHelper(null);

        payload.put("content", this.content);
        payload.put("tts", this.tts);

        if (this.url == null) {
            throw new RuntimeException("URL NOT FOUND OR INVALID!");
        }

        URL url = new URL(this.url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Authorization", TOKEN);
        connection.addRequestProperty("User-Agent", "tl0x-SelfMessage");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(payload.toString().getBytes("UTF-8"));
        stream.flush();
        stream.close();

        connection.getInputStream().close();
        connection.disconnect();
    }
}
