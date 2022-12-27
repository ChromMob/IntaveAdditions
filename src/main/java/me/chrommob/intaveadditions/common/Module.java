package me.chrommob.intaveadditions.common;

import java.util.Set;

public interface Module {
    void init();
    String getName();
    Set<ListenerInterface> getListeners();

}
