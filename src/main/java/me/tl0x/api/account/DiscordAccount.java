package me.tl0x.api.account;

import me.tl0x.internal.server.DiscordServerImpl;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 * A useful class used to retrieve information from a Discord Account
 *
 * @author Aaron (tl0x)
 */
public interface DiscordAccount {

    @Nullable
    Double getFlags();

    /**
     * Returns the unique identifier of the discord account, usually a large integer.
     */
    String getId();

    String getBannerColor();

    String getAvatarHash();

    String getBannerHash();

    /**
     * Returns the current user's Biography (Or the "About Me" section)
     **/
    String getBio();

    String getDiscriminator();

    /**
     *  Returns a JSON String containing data about the Discord User
     */
    String getData();

    /**
     * Returns the email associated with the account
     */
    String getEmail();

    /**
     * Returns the language the account is set to.
     */
    String getLocale();

    String getPhone();

    @Nullable
    String getPronouns();

    String getUsername();

    String getUserTag();

    /**
     * Sends a message to a channel with the given Account Token.
     *
     * @param channelId The id of the channel
     * @param content The message to be sent
     * @param tts set to True to use tts.
     * @throws IOException Thrown when given an invalid Channel ID or Token
     */
    void sendMessage(String channelId, String content, boolean tts) throws IOException;

    /**
     * @return A list of DiscordServers
     * @throws IOException
     */
    List<DiscordServerImpl> getServers() throws IOException;
}
