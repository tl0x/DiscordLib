package me.tl0x.message;

import me.tl0x.util.*;

import javax.net.ssl.HttpsURLConnection;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DiscordWebhook {

    // The main class, probably the only one the end user will use (Except with WebhookBuilder)

    private final String url;
    private String content;
    private String username;
    private String avatarUrl;
    private boolean tts;
    private List<EmbedObject> embeds = new ArrayList<>();

    public DiscordWebhook(String url) {
        this.url = url;
    }

    public void setContent(String content) {
        if (content == null) {
            return;
        }
        this.content = content;
    }

    public void setUsername(String username) {
        if (username == null) {
            return;
        }
        this.username = username;
    }

    public void setAvatarUrl(String avatarUrl) {
        if (avatarUrl == null) {
            return;
        }
        this.avatarUrl = avatarUrl;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public void addEmbed(EmbedObject embed) {
        this.embeds.add(embed);
    }

    public void setEmbeds(List<EmbedObject> embeds) {
        this.embeds = embeds;
    }


    // Runs and attempts to send the content.

    public void execute() throws IOException {
        if (this.content == null && this.embeds.isEmpty()) {
            throw new IllegalArgumentException("No content given, ignoring request.");
        }

        JsonHelper json = new JsonHelper(null);

        json.put("content", this.content);
        json.put("username", this.username);
        json.put("avatar_url", this.avatarUrl);
        json.put("tts", this.tts);

        if (!this.embeds.isEmpty()) {
            List<JsonHelper> embedObjects = new ArrayList<>();

            for (EmbedObject embed : this.embeds) {
                JsonHelper jsonEmbed = new JsonHelper(null);

                jsonEmbed.put("title", embed.getTitle());
                jsonEmbed.put("description", embed.getDescription());
                jsonEmbed.put("url", embed.getUrl());

                if (embed.getColor() != null) {
                    Color color = embed.getColor();
                    int rgb = color.getRed();
                    rgb = (rgb << 8) + color.getGreen();
                    rgb = (rgb << 8) + color.getBlue();

                    jsonEmbed.put("color", rgb);
                }

                WebhookFooter footer = embed.getFooter();
                EmbedImage image = embed.getImage();
                EmbedThumbnail thumbnail = embed.getThumbnail();
                EmbedAuthor author = embed.getAuthor();

                List<EmbedField> fields = embed.getFields();

                if (footer != null) {
                    JsonHelper jsonFooter = new JsonHelper(null);

                    jsonFooter.put("text", footer.getText());
                    jsonFooter.put("icon_url", footer.getIconUrl());
                    jsonEmbed.put("footer", jsonFooter);
                }

                if (image != null) {
                    JsonHelper jsonImage = new JsonHelper(null);

                    jsonImage.put("url", image.getUrl());
                    jsonEmbed.put("image", jsonImage);
                }

                if (thumbnail != null) {
                    JsonHelper jsonThumbnail = new JsonHelper(null);

                    jsonThumbnail.put("url", thumbnail.getUrl());
                    jsonEmbed.put("thumbnail", jsonThumbnail);
                }

                if (author != null) {
                    JsonHelper jsonAuthor = new JsonHelper(null);

                    jsonAuthor.put("name", author.getName());
                    jsonAuthor.put("url", author.getUrl());
                    jsonAuthor.put("icon_url", author.getIconUrl());
                    jsonEmbed.put("author", jsonAuthor);
                }

                List<JsonHelper> jsonFields = new ArrayList<>();
                for (EmbedField field : fields) {
                    JsonHelper jsonField = new JsonHelper(null);

                    jsonField.put("name", field.getName());
                    jsonField.put("value", field.getValue());
                    jsonField.put("inline", field.isInline());

                    jsonFields.add(jsonField);
                }

                jsonEmbed.put("fields", jsonFields.toArray());
                embedObjects.add(jsonEmbed);
            }

            json.put("embeds", embedObjects.toArray());
        }

        URL url = new URL(this.url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "tl0x-Webhook");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream stream = connection.getOutputStream();
        stream.write(json.toString().getBytes("UTF-8"));
        stream.flush();
        stream.close();

        connection.getInputStream().close();
        connection.disconnect();
    }

}