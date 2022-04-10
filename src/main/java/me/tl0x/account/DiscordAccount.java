package me.tl0x.account;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import me.tl0x.server.DiscordServer;
import me.tl0x.util.JsonHelper;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


/**
 * A useful class used to retrieve information from a Discord Account
 *
 * @author Aaron (tl0x)
 */
public class DiscordAccount {

    /** The token associated with a Non-bot account **/
    private final String TOKEN;

    /** Json String containing data about the Discord User **/
    private String data;
    private Double flags;
    private boolean isVerified;
    private String bannerHash;
    private String bio;
    private String avatarHash;

    /** The language setting the account is on **/
    private String locale;
    private String discriminator;
    private String username;
    private String bannerColor;
    private String phone;
    private String pronouns;

    /** The account's id, usually a large integer **/
    private String id;

    /** The email associated with the account **/
    private String email;
    private boolean nsfwAllowed;
    private boolean isMfaEnabled;


    /**
     * Class representing a Discord Account, info fetched when Initiated.
     *
     * @param token The user token associated with an account
     * @throws IOException When given an Invalid Token
     */
    public DiscordAccount(String token) throws IOException {
        this.TOKEN = token;
        URL url = new URL("https://discord.com/api/v6/users/@me");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Authorization", this.TOKEN);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            this.data += inputLine;
        }
        // Why does it always start with "null"?
        if (this.data.startsWith("null")) {
            this.data = this.data.substring(4);
        }

        reader.close();

        JsonHelper jsonData = JsonHelper.fromString(data);
        Map<String, Object> jsonMap = jsonData.map;

        this.flags = (Double) jsonMap.get("flags");
        this.id = (String) jsonMap.get("id");
        this.isVerified = (Boolean) jsonMap.get("verified");
        this.avatarHash = (String) jsonMap.get("avatar");
        this.discriminator = (String) jsonMap.get("discriminator");
        this.bannerColor = (String) jsonMap.get("banner_color");
        this.bio = (String) jsonMap.get("bio");
        this.pronouns = (String) jsonMap.get("pronouns");
        this.locale = (String) jsonMap.get("locale");
        this.nsfwAllowed = (Boolean) jsonMap.get("nsfw_allowed");
        this.isMfaEnabled = (Boolean) jsonMap.get("mfa_enabled");
        this.email = (String) jsonMap.get("email");
        this.phone = (String) jsonMap.get("phone");
        this.username = (String) jsonMap.get("username");
    }

    public Double getFlags() {
        return flags;
    }

    public String getId() {
        return id;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public String getAvatarHash() {
        return avatarHash;
    }

    public String getBannerHash() {
        return bannerHash;
    }

    public String getBio() {
        return bio;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public String getData() {
        return data;
    }

    public String getEmail() {
        return email;
    }

    public String getLocale() {
        return locale;
    }

    public String getPhone() {
        return phone;
    }

    public String getPronouns() {
        return pronouns;
    }

    public String getUsername() {
        return username;
    }

    public String getUserTag() {
        return username + "#" + discriminator;
    }


    public List<DiscordServer> getServers() throws IOException, MalformedURLException {
        List<DiscordServer> discordServers = null;
        URL url = new URL("https://discord.com/api/v6/users/@me/guilds");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Authorization", this.TOKEN);


        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            Gson gson = new GsonBuilder().setLenient().create();
            JsonArray object = JsonParser.parseString(inputLine).getAsJsonArray();

             discordServers = gson.fromJson(object, new TypeToken<List<DiscordServer>>(){}.getType());
        }
        return discordServers;
    }

    public void sendMessage(String channelId, String content) throws IOException{
        this.sendMessage(channelId, content, false);
    }

    /**
     * Sends a message to a channel with the given Account Token.
     *
     * @param channelId The id of the channel
     * @param content The message to be sent
     * @param tts set to True to use tts.
     * @throws IOException Thrown when given an invalid Channel ID or Token
     */
    public void sendMessage(String channelId, String content, boolean tts) throws IOException {

        String strurl = "https://discordapp.com/api/v6/channels/" + channelId + "/messages";
        JsonHelper payload = new JsonHelper(null);
        payload.put("content", content);
        payload.put("tts", tts);

        if (strurl == null) {
            throw new RuntimeException("URL NOT FOUND OR INVALID!");
        }

        URL url = new URL(strurl);
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
