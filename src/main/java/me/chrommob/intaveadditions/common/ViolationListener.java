package me.chrommob.intaveadditions.common;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import de.jpx3.intave.access.check.event.IntaveViolationEvent;
import me.chrommob.intaveadditions.IntaveAdditionsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ViolationListener implements Listener {
    private final IntaveAdditionsPlugin plugin;
    public ViolationListener(IntaveAdditionsPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onDetection(IntaveViolationEvent event) {
        plugin.getEventDispatcher().violationEvent(event);
    }

    @EventHandler
    public void onCommand(IntaveCommandExecutionEvent event) {
        plugin.getEventDispatcher().commandEvent(event);
    }
}
