package me.chrommob.intavediscord.module;

import java.util.HashMap;
import java.util.Map;

public final class ModuleRepository {
  private final Map<Class<?>, Module> modules = new HashMap<>();

  public <T extends Module> T moduleOf(Class<T> clazz) {
    return (T) modules.get(clazz);
  }

  void addModule(Module module) {
    modules.put(module.getClass(), module);
  }
}
