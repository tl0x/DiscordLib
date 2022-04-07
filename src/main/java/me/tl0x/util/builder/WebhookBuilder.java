package me.tl0x.util.builder;

import me.tl0x.account.DiscordWebhook;
import me.tl0x.util.EmbedObject;

import java.util.ArrayList;
import java.util.List;

public class WebhookBuilder {

    /*
    An optional builder for our favourite, Discord Webhook.
     */

    private String url;
    private String content;
    private String username;
    private String avatarUrl;
    private boolean useTTs;
    private List<EmbedObject> embeds = new ArrayList<>();

    public WebhookBuilder() {
        this.content = null;
        this.url = null;
        this.avatarUrl = null;
        this.useTTs = false;
    }

    public WebhookBuilder url(String url) {
        this.url = url;
        return this;
    }

    public WebhookBuilder content(String content) {
        this.content = content;
        return this;
    }

    public WebhookBuilder username(String username) {
        this.username = username;
        return this;
    }

    public WebhookBuilder avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public WebhookBuilder useTTs(boolean useTTs){
        this.useTTs = useTTs;
        return this;
    }

    public WebhookBuilder addEmbed(EmbedObject object) {
        this.embeds.add(object);
        return this;
    }

    public DiscordWebhook build() {
        if ((this.content == null) && (this.embeds.isEmpty())) {
            throw new IllegalStateException("You must specify content or add an EmbedObject!");
        } else if (this.url == null) {
            throw new IllegalStateException("No discord webhook URL given!");
        } else {
            DiscordWebhook ret = new DiscordWebhook(this.url);
            ret.setContent(this.content);
            ret.setUsername(this.username);
            ret.setAvatarUrl(this.avatarUrl);
            ret.setTts(this.useTTs);
            ret.setEmbeds(this.embeds);

            return ret;
        }
    }
}
