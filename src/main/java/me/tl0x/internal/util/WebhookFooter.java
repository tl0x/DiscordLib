package me.tl0x.internal.util;

/**
 * Represents a Webhook Footer
 */
public class WebhookFooter {
    public String text;
    public String iconUrl;

    public WebhookFooter(String text, String iconUrl) {
        this.text = text;
        this.iconUrl = iconUrl;
    }

    /**
     * Returns the text content of the Footer
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the url of the icon
     */
    public String getIconUrl() {
        return iconUrl;
    }
}