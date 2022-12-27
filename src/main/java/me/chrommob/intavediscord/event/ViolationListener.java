package me.chrommob.intavediscord.event;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import de.jpx3.intave.access.check.event.IntaveViolationEvent;
import me.chrommob.intavediscord.IntaveDiscordPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ViolationListener implements Listener {
  private final IntaveDiscordPlugin plugin;

  public ViolationListener(IntaveDiscordPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onDetection(IntaveViolationEvent event) {
    plugin.eventDispatch().violationEvent(event);
  }

  @EventHandler
  public void onCommand(IntaveCommandExecutionEvent event) {
    plugin.eventDispatch().commandEvent(event);
  }
}
