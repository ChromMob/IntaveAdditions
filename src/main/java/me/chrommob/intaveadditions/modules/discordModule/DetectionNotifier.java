package me.chrommob.intaveadditions.modules.discordModule;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import me.chrommob.intaveadditions.common.ListenerInterface;

public class DetectionNotifier implements ListenerInterface {
    private final DiscordModule discordModule;
    public DetectionNotifier(DiscordModule discordModule) {
        this.discordModule = discordModule;
    }
    @Override
    public void commandEvent(IntaveCommandExecutionEvent event) {
        discordModule.sendWebhook(event.player().getDisplayName(), event.command(), event.violationCheck(), event.activationVL(), event.violationDetails());
    }
}
