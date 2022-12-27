package me.chrommob.intaveadditions.modules.discordModule;

import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import me.chrommob.intaveadditions.common.ConfigReader;
import me.chrommob.intaveadditions.common.ListenerInterface;
import me.chrommob.intaveadditions.common.Module;

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

    @Override
    public void init() {
        configReader = plugin.getConfigReader();
        try {
            WebhookMessage webHookMessage = createTemplateMessage();
            webHookMessage.setContent(configReader.prefix() + " has been enabled!");
            webHookMessage.execute();
        } catch (IOException ioException) {
            plugin.getLogger().info("Discord module failed to initialize.");
        }
    }

    public void sendWebhook(String username, String command, String check, double violationLevel, String violationDetails) {
        WebhookMessage message = createTemplateMessage();
        message.addEmbed(new WebhookMessage.EmbedObject()
                .setTitle(configReader.prefix() + " Detection")
                .setDescription(username)
                .addField("Command", command, false)
                .addField("Check", check, false)
                .addField("Violation Level", String.valueOf(violationLevel), false)
                .addField("Violation Details", violationDetails, false)
                .addField("Violation Level", String.valueOf(violationLevel), false)
                .setColor(Color.RED));
        try {
            message.execute();
        } catch (IOException ioException) {
            plugin.getLogger().info("Failed to send webhook message. Probably due to rate limiting.");
        }
    }

    private WebhookMessage createTemplateMessage() {
        WebhookMessage message = new WebhookMessage(configReader.webhookUrl());
        message.setUsername(configReader.username());
        message.setAvatarUrl(configReader.avatarUrl());
        return message;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public Set<ListenerInterface> getListeners() {
        return Collections.singleton(new DetectionNotifier(this));
    }
}
