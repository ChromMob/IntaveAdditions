package me.chrommob.intaveadditions.module;

import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import me.chrommob.intaveadditions.event.Subscriber;
import me.chrommob.intaveadditions.module.discord.DiscordModule;
import me.chrommob.intaveadditions.module.falseFlags.FalseFlagModule;

public final class Modules {
  private final IntaveAdditionsPlugin plugin;
  private final ModuleRepository repository;

  public Modules(IntaveAdditionsPlugin plugin) {
    this.plugin = plugin;
    this.repository = new ModuleRepository();
  }

  public void loadAll() {
    load(new DiscordModule(plugin));
    load(new FalseFlagModule(plugin));
  }

  private void load(Module module) {
    if (!module.isEnabled()) {
        return;
    }
    module.readConfig();
    module.init();
    for (Subscriber listener : module.subscriptions()) {
      plugin.eventDispatch().registerListeners(listener);
    }
    plugin.getLogger().info("Registered module " + module.name());
    repository.addModule(module);
  }

  public <T extends Module> T moduleOf(Class<T> clazz) {
    return repository.moduleOf(clazz);
  }
}
