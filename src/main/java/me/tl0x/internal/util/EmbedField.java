package me.tl0x.internal.util;

/**
 * Represents an Embedded Field
 */
public class EmbedField {
    public String name;
    public String value;
    public boolean inline;

    public EmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    /**
     * @return the name of the field
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the field
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns if the field is inline
     * @return
     */
    public boolean isInline() {
        return inline;
    }
}