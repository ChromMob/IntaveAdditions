package me.chrommob.intaveadditions.event;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import de.jpx3.intave.access.check.event.IntaveViolationEvent;

public interface Subscriber {

  default void selectEvent(IntaveViolationEvent event) {
  }

  default void violationEvent(IntaveViolationEvent event) {
  }

  default void onAttackRaytrace(IntaveViolationEvent event) {
  }

  default void onBreakSpeedLimiter(IntaveViolationEvent event) {
  }

  default void onClickPatterns(IntaveViolationEvent event) {
  }

  default void onClickSpeedLimiter(IntaveViolationEvent event) {
  }

  default void onHeuristics(IntaveViolationEvent event) {
  }

  default void onInteractionRaytrace(IntaveViolationEvent event) {
  }

  default void onInventoryClickAnalysis(IntaveViolationEvent event) {
  }

  default void onPhysics(IntaveViolationEvent event) {
  }

  default void onPlacementAnalysis(IntaveViolationEvent event) {
  }

  default void onProtocolScanner(IntaveViolationEvent event) {
  }


  default void onTimer(IntaveViolationEvent event) {
  }

  default void commandEvent(IntaveCommandExecutionEvent event) {
  }
}
