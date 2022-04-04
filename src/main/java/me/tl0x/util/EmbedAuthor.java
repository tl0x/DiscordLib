package me.tl0x.util;

public class EmbedAuthor {
    public String name;
    public String url;
    public String iconUrl;

    public EmbedAuthor(String name, String url, String iconUrl) {
        this.name = name;
        this.url = url;
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}