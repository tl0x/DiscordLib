package me.tl0x.util.builder;

import me.tl0x.rpc.DiscordRichPresence;

public class RichPrescenceBuilder {

    private DiscordRichPresence p;

    public RichPrescenceBuilder(String state) {
        p = new DiscordRichPresence();
        p.state = state;
    }

    public RichPrescenceBuilder setDetails(String details) {
        p.details = details;
        return this;
    }

    public RichPrescenceBuilder setStartTimestamps(long start) {
        p.startTimestamp = start;
        return this;
    }

    public RichPrescenceBuilder setEndTimestamp(long end) {
        p.endTimestamp = end;
        return this;
    }

    public RichPrescenceBuilder setBigImage(String key, String text) {
        if ((text != null && !text.equalsIgnoreCase("")) && key == null)
            throw new IllegalArgumentException("Image key must not be null when assigning a hover text.");

        p.largeImageKey = key;
        p.largeImageText = text;
        return this;
    }

    public RichPrescenceBuilder setSmallImage(String key, String text) {
        if ((text != null && !text.equalsIgnoreCase("")) && key == null)
            throw new IllegalArgumentException("Image key must not be null when assigning a hover text.");

        p.smallImageKey = key;
        p.smallImageText = text;
        return this;
    }

    public RichPrescenceBuilder setParty(String party, int size, int max) {
        p.partyId = party;
        p.partySize = size;
        p.partyMax = max;
        return this;
    }


    public RichPrescenceBuilder setSecrets(String join, String spectate) {
        p.joinSecret = join;
        p.spectateSecret = spectate;
        return this;
    }

    public DiscordRichPresence build() {
        return p;
    }
}