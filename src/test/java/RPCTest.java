import me.tl0x.rpc.DiscordEventHandlers;
import me.tl0x.rpc.DiscordRpcManager;
import me.tl0x.util.builder.RichPrescenceBuilder;

public class RPCTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        DiscordRpcManager.initialize("940786718594969662", new DiscordEventHandlers.Builder().build());
        DiscordRpcManager.updatePresence(new RichPrescenceBuilder("I LOVE LUV MICHEAL")
                .setBigImage("cheng", "Cheng").setStartTimestamps(start).build());

        Thread.sleep(1000000);
    }
}
