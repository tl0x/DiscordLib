package me.tl0x.server;

import me.tl0x.annotation.Unstable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class DiscordServer {
    private String permissions_new;
    private String id;
    private String name;
    private String icon;
    private String owner;
    private String permissions;
    private String[] features;


    public DiscordServer(String id, String name, String icon, String owner, String permissions, String[] features, String premissions_n) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.permissions = permissions;
        this.features = features;
        this.permissions_new = premissions_n;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getPermissions_new() {
        return permissions_new;
    }

    public String[] getFeatures() {
        return features;
    }


    // TODO: MAKE THIS WORK

    @Unstable
    public List<DiscordChannel> getDiscordChannels(String token) throws IOException {
        // Token is needed to check if you are in the server

        URL url = new URL("https://discord.com/api/v6/guilds/" + this.id);
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("Authorization", token);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            System.out.println(inputLine);
        }

        reader.close();

        return null;
    }
}
