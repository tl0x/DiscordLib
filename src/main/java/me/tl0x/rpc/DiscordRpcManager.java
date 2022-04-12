package me.tl0x.rpc;

import com.sun.jna.Library;

/**
 * Used for initializing a Discord Rich Presence Instance.
 *
 * @author Aaron (tl0x)
 */
public class DiscordRpcManager {

    private static DiscordLib LIBRARY = DiscordRpcUtil.loadLibrary();

    /**
     * Starts a DiscordRPC Process
     * @param id
     * @param callback
     */
    public static void initialize(String id, DiscordEventHandlers callback) {
        LIBRARY.Discord_Initialize(id, callback, 1, null);
    }

    /**
     * Runs all registered Discord Event Callbacks
     */
    public static void runCallbacks() {
        LIBRARY.Discord_RunCallbacks();
    }

    /**
     * Updates the state of the discord presence
     * @param presence the state of the discord presence
     */
    public static void updatePresence(DiscordRichPresence presence) {
        LIBRARY.Discord_UpdatePresence(presence);
    }

    /**
     * Clears the current presence of all properties
     */
    public static void clearPresence() {
        LIBRARY.Discord_ClearPresence();
    }

    public static void respond(String userId, int reply) {
        LIBRARY.Discord_Respond(userId, reply);
    }

    /**
     * Stops the DiscordRpc Instance
     */
    public static void shutdown() {
        LIBRARY.Discord_Shutdown();
    }

    interface DiscordLib extends Library {

        void Discord_Initialize(String applicationId, DiscordEventHandlers handlers, int autoRegister, String optionalSteamId);
        void Discord_Register(String applicationId, String command);
        void Discord_RegisterSteamGame(String applicationId, String steamId);
        void Discord_UpdateHandlers(DiscordEventHandlers handlers);
        void Discord_Shutdown();
        void Discord_RunCallbacks();
        void Discord_UpdatePresence(DiscordRichPresence presence);
        void Discord_ClearPresence();
        void Discord_Respond(String userId, int reply);
    }


    /**
     * A utility class made for Error Handling
     */
    static class DummyDiscordLib implements DiscordLib {

        public void Discord_Initialize(String applicationId, DiscordEventHandlers handlers, int autoRegister, String optionalSteamId) {}
        public void Discord_Register(String applicationId, String command) {}
        public void Discord_RegisterSteamGame(String applicationId, String steamId) {}
        public void Discord_UpdateHandlers(DiscordEventHandlers handlers) {}
        public void Discord_Shutdown() {}
        public void Discord_RunCallbacks() {}
        public void Discord_UpdatePresence(DiscordRichPresence presence) {}
        public void Discord_ClearPresence() {}
        public void Discord_Respond(String userId, int reply) {}

    }
}
