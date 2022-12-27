package me.chrommob.intaveadditions.modules.discordModule;

import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import me.chrommob.intaveadditions.common.ConfigReader;
import me.chrommob.intaveadditions.common.ListenerInterface;
import me.chrommob.intaveadditions.common.Module;
import org.bukkit.configuration.ConfigurationSection;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class DiscordModule implements Module {
    private final IntaveAdditionsPlugin plugin;
    public DiscordModule(IntaveAdditionsPlugin plugin) {
        this.plugin = plugin;
    }

    private ConfigReader configReader;
    private DiscordConfig discordConfig;

    @Override
    public void init() {
        configReader = plugin.getConfigReader();
        discordConfig = (DiscordConfig) configReader.getConfig(this);
        try {
            WebhookMessage webHookMessage = createTemplateMessage();
            webHookMessage.setContent(discordConfig.prefix() + " has been enabled!");
            webHookMessage.execute();
        } catch (IOException ioException) {
            plugin.getLogger().info("Discord module failed to initialize.");
        }
    }

    public void readConfig() {
        configReader = plugin.getConfigReader();
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("discord");
        if (section == null) {
            plugin.getLogger().info("Discord module failed to read config.");
            return;
        }
        DiscordConfig discordConfig;
        if (configReader.getConfig(this) == null) {
            discordConfig = new DiscordConfig();
        } else {
            discordConfig = (DiscordConfig) configReader.getConfig(this);
        }
        discordConfig.setWebhookUrl(section.getString("webhook"));
        discordConfig.setUsername(section.getString("username"));
        discordConfig.setAvatarUrl(section.getString("avatarUrl"));
        discordConfig.setPrefix(section.getString("prefix"));
        configReader.addConfig(this, discordConfig);
    }

    public void sendWebhook(String username, String command, String check, double violationLevel, String violationDetails) {
        WebhookMessage message = createTemplateMessage();
        message.addEmbed(new WebhookMessage.EmbedObject()
                .setTitle(discordConfig.prefix() + " Detection")
                .setDescription(username)
                .addField("Command", command.replaceAll("§.", ""), false)
                .addField("Check", check, true)
                .addField("VL", String.valueOf(violationLevel), true)
                .addField("Violation Details", violationDetails, false)
                .setThumbnail("https://cravatar.eu/avatar/" + username + "/64.png")
                .setColor(Color.RED));
        try {
            message.execute();
        } catch (IOException ioException) {
            plugin.getLogger().info("Failed to send webhook message. Probably due to rate limiting.");
        }
    }
    private WebhookMessage createTemplateMessage() {
        WebhookMessage message = new WebhookMessage(discordConfig.webhookUrl());
        message.setUsername(discordConfig.username());
        message.setAvatarUrl(discordConfig.avatarUrl());
        return message;
    }


    @Override
    public String getName() {
        return "DiscordModule";
    }

    @Override
    public Set<ListenerInterface> getListeners() {
        return Collections.singleton(new DetectionNotifier(this));
    }
}
