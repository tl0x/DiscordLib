package me.tl0x.api.account;

import me.tl0x.internal.util.EmbedObject;

import java.io.IOException;
import java.util.List;

/**
 * A useful class used for sending payloads to Discord Webhooks.
 *
 * @author Aaron (tl0x)
 */
public interface DiscordWebhook {

    void setContent(String content);

    void setUsername(String username);

    void setAvatarUrl(String avatarUrl);

    void setTts(boolean tts);

    void addEmbed(EmbedObject embed);

    void setEmbeds(List<EmbedObject> embeds);

    /**
     * Sends a message given contents and embeds
     * @throws IOException
     */
    void sendPayload() throws IOException;
}
