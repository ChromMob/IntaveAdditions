package me.chrommob.intavediscord;

import me.chrommob.intavediscord.event.EventDispatcher;
import me.chrommob.intavediscord.module.ModuleLoader;
import me.chrommob.intavediscord.module.Modules;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IntaveDiscordPlugin extends JavaPlugin {
  private Modules modules;
  private EventDispatcher eventDispatcher;

  @Override
  public void onEnable() {
    Logger logger = getLogger();

    // Plugin startup logic
    loadConfig();
    eventDispatcher = new EventDispatcher(this);
    loadModules();

    logger.info("Startup complete");
  }

  private void loadConfig() {
    saveDefaultConfig();
    reloadConfig();
  }

  private void loadModules() {
    modules = new Modules(this);
    modules.loadAll();
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public Modules modules() {
    return modules;
  }

  public EventDispatcher eventDispatch() {
    return eventDispatcher;
  }
}
