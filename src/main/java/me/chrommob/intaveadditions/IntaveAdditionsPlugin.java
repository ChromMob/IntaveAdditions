package me.chrommob.intaveadditions;

import me.chrommob.intaveadditions.common.ConfigReader;
import me.chrommob.intaveadditions.common.EventDispatcher;
import me.chrommob.intaveadditions.common.ModulesInitializer;
import me.chrommob.intaveadditions.modules.discordModule.DiscordModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class IntaveAdditionsPlugin extends JavaPlugin {
    private ConfigReader configReader;
    private ModulesInitializer modulesInitializer;
    private EventDispatcher eventDispatcher;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();
        configReader = new ConfigReader();
        loadConfig();
        eventDispatcher = new EventDispatcher(this);
        initModules();
    }

    private void loadConfig() {
        configReader.setWebhookUrl(getConfig().getString("webhookUrl"));
        configReader.setUsername(getConfig().getString("username"));
        configReader.setAvatarUrl(getConfig().getString("avatarUrl"));
        configReader.setPrefix(getConfig().getString("prefix"));
    }

    private void initModules() {
        modulesInitializer = new ModulesInitializer(this);
        modulesInitializer.registerModule(new DiscordModule(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigReader getConfigReader() {
        return configReader;
    }

    public EventDispatcher getEventDispatcher() {
        return eventDispatcher;
    }
}
