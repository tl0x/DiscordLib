package me.tl0x.api.server;

import java.io.IOException;
import java.util.List;

public interface DiscordServer {

    String getName();

    String getId();

    String getOwner();

    String getPermissions();

    String getPermissions_new();

    String[] getFeatures();

    List<DiscordChannel> getDiscordChannels(String token) throws IOException;
}
