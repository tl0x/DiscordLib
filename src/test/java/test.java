import me.tl0x.util.WebhookBuilder;
import me.tl0x.webhook.DiscordWebhook;
import me.tl0x.webhook.EmbedObject;

import java.awt.*;
import java.io.IOException;

// Literally my first time testing

public class test {

    public static void main(String[] args) throws IOException {
        EmbedObject embedFail = new EmbedObject().setTitle("Haha").setDescription("This is an embed")
                .setThumbnail("https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg")
                .setColor(25,25,25);

        DiscordWebhook webhook = new WebhookBuilder().username("Powerful Man")
                .content("content is here")
                .url("https://discord.com/api/webhooks/960190684671901726/hpv_EUNBMvxZs6B_-SiyeXDyivem6RwFXhbaUD3scZPDbV-Hk3q3ogHor7UUzstw3X0a")
                .addEmbed(embedFail)
                .build();


        webhook.execute();
    }
}
