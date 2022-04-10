package me.tl0x.rpc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Used for initializing a Discord Rich Presence Instance.
 *
 * @author Aaron (tl0x)
 */
public class DiscordRPCManager {

    private static DiscordLib LIBRARY = loadLibrary();

    private static DiscordLib loadLibrary() {
        try {
            String libName;
            if (SystemUtils.IS_OS_MAC) {
                libName = "discord-rpc-darwin.dylib";
            } else if (SystemUtils.IS_OS_WINDOWS) {
                libName = "discord-rpc-win-" + (System.getProperty("sun.arch.data.model").equals("64") ? "x64" : "x86") + ".dll";
            } else if ("The Android Project".equals(System.getProperty("java.specification.vendor"))) {
                return new DummyDiscordLib();
            } else {
                libName = "linux";
            }

            File file = new File(System.getProperty("java.io.tmpdir"), libName);
            file.mkdirs();

            InputStream is = DiscordRPCManager.class.getResourceAsStream("/rpc/" + libName);
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            is.close();

            DiscordLib lib = Native.load(file.getAbsolutePath(), DiscordLib.class);

            return lib;
        } catch (IOException e) {
            return new DummyDiscordLib();
        }
    }

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

    public static void clearPresence() {
        LIBRARY.Discord_ClearPresence();
    }

    public static void respond(String userId, int reply) {
        LIBRARY.Discord_Respond(userId, reply);
    }

    public static void shutdown() {
        LIBRARY.Discord_Shutdown();
    }

    private interface DiscordLib extends Library {

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

    private static class DummyDiscordLib implements DiscordLib {

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
