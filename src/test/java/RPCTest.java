import me.tl0x.rpc.DiscordEventHandlers;
import me.tl0x.rpc.DiscordRPCManager;
import me.tl0x.rpc.DiscordRichPresence;
import me.tl0x.util.builder.RichPrescenceBuilder;

public class RPCTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        DiscordRPCManager.initialize("940786718594969662", new DiscordEventHandlers.Builder().build());
        DiscordRPCManager.updatePresence(new RichPrescenceBuilder("fortniting")
                .setBigImage("freddiedonut", "fortniting").setStartTimestamps(start).build());

        Thread.sleep(50000);

    }
}