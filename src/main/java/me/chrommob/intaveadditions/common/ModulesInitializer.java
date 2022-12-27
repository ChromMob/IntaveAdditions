package me.chrommob.intaveadditions.common;

import me.chrommob.intaveadditions.IntaveAdditionsPlugin;

public class ModulesInitializer {
    private final IntaveAdditionsPlugin plugin;
    public ModulesInitializer(IntaveAdditionsPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerModule(Module module) {
        module.init();
        for (ListenerInterface listener : module.getListeners()) {
            plugin.getEventDispatcher().registerListeners(listener);
        }
        plugin.getLogger().info("Registered module " + module.getName());
    }
}
