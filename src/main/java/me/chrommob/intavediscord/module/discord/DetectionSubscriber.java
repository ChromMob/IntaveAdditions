package me.chrommob.intavediscord.module.discord;

import de.jpx3.intave.access.check.Check;
import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import de.jpx3.intave.access.check.event.IntaveViolationEvent;
import me.chrommob.intavediscord.event.Subscriber;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DetectionSubscriber implements Subscriber {
  private final DiscordModule discordModule;
  private final Map<String, Map<Check, Double>> violations = new ConcurrentHashMap<>();

  public DetectionSubscriber(DiscordModule discordModule) {
    this.discordModule = discordModule;
  }

  @Override
  public void commandEvent(IntaveCommandExecutionEvent event) {
    discordModule.sendWebhook(event.player().getDisplayName(), event.command(), event.violationCheck(), getViolationCountMessage(event.player().getDisplayName(), Check.fromName(event.violationCheck()), event.activationVL()), event.violationDetails());
  }

  @Override
  public void violationEvent(IntaveViolationEvent event) {
    violations.putIfAbsent(event.player().getDisplayName(), new ConcurrentHashMap<>());
    violations.get(event.player().getDisplayName()).put(event.checkEnum(), event.violationLevelBeforeViolation());
  }

  private String getViolationCountMessage(String name, Check check, double violationLevel) {
    double difference;
    double before;
    if (violations.containsKey(name)) {
      before = violations.get(name).getOrDefault(check, 0.0D);
      difference = violationLevel - before;
    } else {
      before = 0.0D;
      difference = 0.0D;
    }
    return before + " (+" + difference + ") -> " + violationLevel;
  }

}
