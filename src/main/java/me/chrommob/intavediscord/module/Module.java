package me.chrommob.intavediscord.module;

import me.chrommob.intavediscord.event.Subscriber;

import java.util.Set;

public interface Module {
  void init();

  void readConfig();

  String name();

  Set<Subscriber> subscriptions();
}
