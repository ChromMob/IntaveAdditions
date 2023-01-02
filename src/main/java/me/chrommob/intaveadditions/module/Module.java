package me.chrommob.intaveadditions.module;

import me.chrommob.intaveadditions.event.Subscriber;

import java.util.Set;

public interface Module {
  void init();

  boolean isEnabled();

  void readConfig();

  String name();

  Set<Subscriber> subscriptions();
}
