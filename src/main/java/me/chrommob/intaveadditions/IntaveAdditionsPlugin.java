package me.chrommob.intaveadditions;

import me.chrommob.intaveadditions.event.EventDispatcher;
import me.chrommob.intaveadditions.module.Modules;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class IntaveAdditionsPlugin extends JavaPlugin {
  private Modules modules;
  private EventDispatcher eventDispatcher;

  @Override
  public void onEnable() {
    Logger logger = getLogger();

    new Metrics(this, 17267);

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
