package me.tl0x.rpc;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class DiscordRichPresence extends Structure {

    /** State of the player's current party. **/
    public String state;
    /** Details to the current game-session of the player. **/
    public String details;
    /** Unix timestamp for the start of the game **/
    public long startTimestamp;
    /** Unix timestamp for when the game will end **/
    public long endTimestamp;
    /** Name of the uploaded image for the large profile artwork. **/
    public String largeImageKey;
    /** Tooltip for when hovering over the Large Image **/
    public String largeImageText;
    /** Name of the uploaded image for the small image **/
    public String smallImageKey;
    /** Tooltip when hovering over smallImage **/
    public String smallImageText;
    /** Id of the player's party, lobby, or group. **/
    public String partyId;
    public int partySize;
    public int partyMax;
    public String matchSecret;
    /** Unique hashed string for Spectate button. **/
    public String spectateSecret;
    /** Unique hashed string for chat invitations and Ask to Join. **/
    public String joinSecret;

    public int instance;

    @Override
    public List<String> getFieldOrder() {
        return Arrays.asList("state", "details", "startTimestamp", "endTimestamp", "largeImageKey", "largeImageText", "smallImageKey", "smallImageText", "partyId", "partySize", "partyMax", "matchSecret", "joinSecret", "spectateSecret", "instance");
    }

}