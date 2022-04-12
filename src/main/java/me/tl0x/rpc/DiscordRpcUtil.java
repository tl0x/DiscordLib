package me.tl0x.rpc;

import com.sun.jna.Native;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DiscordRpcUtil {

    static DiscordRpcManager.DiscordLib loadLibrary() {
        try {
            String libName;
            if (SystemUtils.IS_OS_MAC) {
                libName = "discord-rpc-darwin.dylib";
            } else if (SystemUtils.IS_OS_WINDOWS) {
                libName = "discord-rpc-win-" + (System.getProperty("sun.arch.data.model").equals("64") ? "x64" : "x86") + ".dll";
            } else if ("The Android Project".equals(System.getProperty("java.specification.vendor"))) {
                return new DiscordRpcManager.DummyDiscordLib();
            } else {
                libName = "discord-rpc-linux.so";
            }

            File file = new File(System.getProperty("java.io.tmpdir"), libName);
            file.mkdirs();

            InputStream is = DiscordRpcManager.class.getResourceAsStream("/rpc/" + libName);
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            is.close();

            DiscordRpcManager.DiscordLib lib = Native.load(file.getAbsolutePath(), DiscordRpcManager.DiscordLib.class);

            return lib;
        } catch (IOException e) {
            return new DiscordRpcManager.DummyDiscordLib();
        }
    }

}
