package me.chrommob.intaveadditions.module.falseFlags;

import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import me.chrommob.intaveadditions.event.Subscriber;
import me.chrommob.intaveadditions.module.Module;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.Set;

public class FalseFlagModule implements Module {
    private final IntaveAdditionsPlugin plugin;
    private FalseFlagConfig falseFlagConfig;

    public FalseFlagModule(IntaveAdditionsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {}

    @Override
    public boolean isEnabled() {
        return plugin.getConfig().getBoolean("falseFlags.enabled");
    }

    @Override
    public void readConfig() {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("falseFlags");
        if (section == null) {
            plugin.getLogger().info("Discord module failed to read config.");
            return;
        }
        FalseFlagConfig falseFlagConfig = new FalseFlagConfig();
        falseFlagConfig.setNotVanillaEnchantments(section.getBoolean("nonVanillaItems"));
        this.falseFlagConfig = falseFlagConfig;
    }

    @Override
    public String name() {
        return "False Flags";
    }

    @Override
    public Set<Subscriber> subscriptions() {
        Set<Subscriber> subscribers = new HashSet<>();
        if (falseFlagConfig.notVanillaEnchantments()) {
            subscribers.add(new NonVanillaSubscriber());
        }
        return subscribers;
    }
}
