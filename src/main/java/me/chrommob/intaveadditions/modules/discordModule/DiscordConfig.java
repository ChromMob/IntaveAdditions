package me.chrommob.intaveadditions.modules.discordModule;

public class DiscordConfig {
    private String webhookUrl;
    private String username;
    private String avatarUrl;
    private String prefix;

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String webhookUrl() {
        return webhookUrl;
    }

    public String username() {
        return username;
    }

    public String avatarUrl() {
        return avatarUrl;
    }

    public String prefix() {
        return prefix;
    }
}
