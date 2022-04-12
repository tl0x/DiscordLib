package me.tl0x.internal.util;

/**
 * Represents on Embedded Author
 */
public class EmbedAuthor {
    public String name;
    public String url;
    public String iconUrl;

    public EmbedAuthor(String name, String url, String iconUrl) {
        this.name = name;
        this.url = url;
        this.iconUrl = iconUrl;
    }

    /**
     * Gets the name of the Author
     * @return The name of the Author
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the url associated with the Author
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the url associated with the icon of the Author.
     */
    public String getIconUrl() {
        return iconUrl;
    }
}