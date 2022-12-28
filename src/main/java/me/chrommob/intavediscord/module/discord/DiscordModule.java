package me.chrommob.intavediscord.module.discord;

import me.chrommob.intavediscord.IntaveDiscordPlugin;
import me.chrommob.intavediscord.event.Subscriber;
import me.chrommob.intavediscord.module.Module;
import org.bukkit.configuration.ConfigurationSection;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class DiscordModule implements Module {
  private final IntaveDiscordPlugin plugin;

  public DiscordModule(IntaveDiscordPlugin plugin) {
    this.plugin = plugin;
  }

  private DiscordConfig discordConfig;

  @Override
  public void readConfig() {
    ConfigurationSection section = plugin.getConfig().getConfigurationSection("discord");
    if (section == null) {
      plugin.getLogger().info("Discord module failed to read config.");
      return;
    }
    DiscordConfig discordConfig = new DiscordConfig();
    discordConfig.setWebhookUrl(section.getString("webhookUrl"));
    discordConfig.setUsername(section.getString("username"));
    discordConfig.setAvatarUrl(section.getString("avatarUrl"));
    discordConfig.setPrefix(section.getString("prefix"));
    this.discordConfig = discordConfig;
  }

  @Override
  public void init() {
    try {
      WebhookMessage webHookMessage = createTemplateMessage();
      webHookMessage.setContent(discordConfig.prefix() + " has been enabled!");
      webHookMessage.execute();
    } catch (IOException ioException) {
      plugin.getLogger().info("Discord module failed to initialize.");
    }
  }

  public void sendWebhook(String username, String command, String check, double violationLevel, String violationDetails) {
    WebhookMessage message = createTemplateMessage();
    message.addEmbed(new WebhookMessage.EmbedObject()
      .setTitle(discordConfig.prefix() + " Detection")
      .setDescription(username)
      .addField("Check", check, true)
      .addField("VL", String.valueOf(violationLevel), true)
      .addField("Command", command.replaceAll("§.", ""), false)
      .addField("Debug", violationDetails, false)
      .setThumbnail("https://cravatar.eu/avatar/" + username + "/64.png")
      .setColor(Color.RED));
    try {
      message.execute();
    } catch (IOException exception) {
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
  public String name() {
    return "DiscordModule";
  }

  @Override
  public Set<Subscriber> subscriptions() {
    return Collections.singleton(new DetectionSubscriber(this));
  }
}
