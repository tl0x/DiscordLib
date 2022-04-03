package me.tl0x.webhook;

public class WebhookFooter {
    public String text;
    public String iconUrl;

    public WebhookFooter(String text, String iconUrl) {
        this.text = text;
        this.iconUrl = iconUrl;
    }

    public String getText() {
        return text;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}