package me.tl0x.rpc;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

public class DiscordEventHandlers extends Structure {
    public DiscordCallback ready;
    public DiscordCallback disconnected;
    public DiscordCallback errored;
    public DiscordCallback joinGame;
    public DiscordCallback spectateGame;
    public DiscordCallback joinRequest;

    @Override
    public List<String> getFieldOrder() {
        return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
    }

    public static class Builder {

        DiscordEventHandlers h;

        public Builder() {
            h = new DiscordEventHandlers();
        }

        public Builder withReadyEventHandler(DiscordCallback r) {
            h.ready = r;
            return this;
        }

        public Builder withDisconnectedEventHandler(DiscordCallback d) {
            h.disconnected = d;
            return this;
        }

        public Builder withErroredEventHandler(DiscordCallback e) {
            h.errored = e;
            return this;
        }

        public Builder withJoinGameEventHandler(DiscordCallback j) {
            h.joinGame = j;
            return this;
        }

        public Builder withSpectateGameEventHandler(DiscordCallback s) {
            h.spectateGame = s;
            return this;
        }

        public Builder withJoinRequestEventHandler(DiscordCallback j) {
            h.joinRequest = j;
            return this;
        }

        public DiscordEventHandlers build() {
            return h;
        }
    }

    public interface DiscordCallback extends Callback {
        void apply(DiscordUser request);
    }

    public static class DiscordUser extends Structure {
        public String userId;
        public String username;
        public String discriminator;
        public String avatar;

        @Override
        public List<String> getFieldOrder() {
            return Arrays.asList("userId", "username", "discriminator", "avatar");
        }
    }

}