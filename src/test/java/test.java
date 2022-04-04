import me.tl0x.util.builder.WebhookBuilder;
import me.tl0x.message.DiscordWebhook;
import me.tl0x.util.EmbedObject;

import java.io.IOException;

// Literally my first time testing

public class test {

    public static void main(String[] args) throws IOException {
        EmbedObject embedFail = new EmbedObject().setTitle("Haha").setDescription("This is an embed")
                .setThumbnail("https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg")
                .setColor(25,25,25);

        DiscordWebhook webhook = new WebhookBuilder().username("Powerful Man")
                .content("content is here")
                .url("")
                .addEmbed(embedFail)
                .build();


        webhook.execute();
    }
}
