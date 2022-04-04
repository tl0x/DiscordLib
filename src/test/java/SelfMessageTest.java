import me.tl0x.message.SelfMessage;

import java.io.IOException;

public class SelfMessageTest {

    public static void main(String[] args) throws IOException {

        // TODO: REMOVE THIS LATER
        SelfMessage selfMessageTest = new SelfMessage("Token Here");
        selfMessageTest.setContent("DiscordLibTest");
        selfMessageTest.setChannelId("id");

        for (int i = 0; i < 5; i++) {
            selfMessageTest.setContent("DiscordLibTest " + i);
            selfMessageTest.sendMessage();
        }

    }
}
