package me.tl0x.webhook;

public class EmbedField {
    public String name;
    public String value;
    public boolean inline;

    public EmbedField(String name, String value, boolean inline) {
        this.name = name;
        this.value = value;
        this.inline = inline;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isInline() {
        return inline;
    }
}