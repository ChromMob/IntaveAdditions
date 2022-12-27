package me.chrommob.intaveadditions.common;

import de.jpx3.intave.access.check.event.IntaveCommandExecutionEvent;
import de.jpx3.intave.access.check.event.IntaveViolationEvent;
import me.chrommob.intaveadditions.IntaveAdditionsPlugin;

import java.util.HashSet;
import java.util.Set;

public class EventDispatcher {
    public EventDispatcher(IntaveAdditionsPlugin plugin) {
        new ViolationListener(plugin);
    }

    private final Set<ListenerInterface> listeners = new HashSet<>();
    public void registerListeners(ListenerInterface listener) {
        listeners.add(listener);
    }

    public void violationEvent(IntaveViolationEvent event) {
        for (ListenerInterface listener : listeners) {
            listener.violationEvent(event);
        }
        switch (event.checkEnum()) {
            case ATTACK_RAYTRACE:
                for (ListenerInterface listener : listeners) {
                    listener.onAttackRaytrace(event);
                }
                break;
            case BREAK_SPEED_LIMITER:
                for (ListenerInterface listener : listeners) {
                    listener.onBreakSpeedLimiter(event);
                }
                break;
            case CLICK_PATTERNS:
                for (ListenerInterface listener : listeners) {
                    listener.onClickPatterns(event);
                }
                break;
            case CLICK_SPEED_LIMITER:
                for (ListenerInterface listener : listeners) {
                    listener.onClickSpeedLimiter(event);
                }
                break;
            case HEURISTICS:
                for (ListenerInterface listener : listeners) {
                    listener.onHeuristics(event);
                }
                break;
            case INTERACTION_RAYTRACE:
                for (ListenerInterface listener : listeners) {
                    listener.onInteractionRaytrace(event);
                }
                break;
            case INVENTORY_CLICK_ANALYSIS:
                for (ListenerInterface listener : listeners) {
                    listener.onInventoryClickAnalysis(event);
                }
                break;
            case PHYSICS:
                for (ListenerInterface listener : listeners) {
                    listener.onPhysics(event);
                }
                break;
            case PLACEMENT_ANALYSIS:
                for (ListenerInterface listener : listeners) {
                    listener.onPlacementAnalysis(event);
                }
                break;
            case PROTOCOL_SCANNER:
                for (ListenerInterface listener : listeners) {
                    listener.onProtocolScanner(event);
                }
                break;
            case TIMER:
                for (ListenerInterface listener : listeners) {
                    listener.onTimer(event);
                }
                break;
        }

    }

    public void commandEvent(IntaveCommandExecutionEvent event) {
        for (ListenerInterface listener : listeners) {
            listener.commandEvent(event);
        }
    }
}
