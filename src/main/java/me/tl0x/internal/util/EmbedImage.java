package me.tl0x.internal.util;

/**
 * Represents an Embedded Image
 */
public class EmbedImage {
    public String url;

    public EmbedImage(String url) {
        this.url = url;
    }

    /**
     * @return associated url of the image
     */
    public String getUrl() {
        return url;
    }
}
