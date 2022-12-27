package me.chrommob.intavediscord.module.discord;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import me.chrommob.intavediscord.event.Subscriber;

public class DetectionSubscriber implements Subscriber {
  private final DiscordModule discordModule;

  public DetectionSubscriber(DiscordModule discordModule) {
    this.discordModule = discordModule;
  }

  @Override
  public void commandEvent(IntaveCommandExecutionEvent event) {
    discordModule.sendWebhook(event.player().getDisplayName(), event.command(), event.violationCheck(), event.activationVL(), event.violationDetails());
  }
}
