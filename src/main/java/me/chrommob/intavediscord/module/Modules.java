package me.chrommob.intavediscord.module;

import me.chrommob.intavediscord.IntaveDiscordPlugin;
import me.chrommob.intavediscord.event.Subscriber;
import me.chrommob.intavediscord.module.discord.DiscordModule;

public final class Modules {
  private final IntaveDiscordPlugin plugin;
  private final ModuleRepository repository;

  public Modules(IntaveDiscordPlugin plugin) {
    this.plugin = plugin;
    this.repository = new ModuleRepository();
  }

  public void loadAll() {
    load(new DiscordModule(plugin));
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
