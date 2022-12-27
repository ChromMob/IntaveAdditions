package me.chrommob.intaveadditions.common;


import java.util.concurrent.ConcurrentHashMap;

public class ConfigReader {
    private final ConcurrentHashMap<String, Object> config = new ConcurrentHashMap<>();
    public void addConfig(Module key, Object value) {
        config.put(key.getName(), value);
    }

    public Object getConfig(Module key) {
        return config.getOrDefault(key.getName(), null);
    }
}
