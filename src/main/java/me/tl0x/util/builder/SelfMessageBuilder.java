package me.tl0x.util.builder;

import me.tl0x.message.SelfMessage;
import me.tl0x.util.EmbedObject;

import java.util.List;

public class SelfMessageBuilder {
    private String TOKEN;
    private String content;
    private String channelId;
    private boolean tts;
    private List<EmbedObject> embeds;

    public SelfMessageBuilder() {

    }

    public SelfMessageBuilder TOKEN(String TOKEN) {
        this.TOKEN = TOKEN;
        return this;
    }

    public SelfMessageBuilder content(String content) {
        this.content = content;
        return this;
    }

    public SelfMessageBuilder channelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public SelfMessageBuilder tts(boolean tts) {
        this.tts = tts;
        return this;
    }

    public SelfMessage build() {
        SelfMessage ret = new SelfMessage(this.TOKEN);
        ret.setContent(this.content);
        ret.setChannelId(this.channelId);
        ret.setTts(tts);

        return ret;
    }
}
