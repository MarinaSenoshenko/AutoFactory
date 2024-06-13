package ru.nsu.ccfit.factory;

import ru.nsu.ccfit.factory.config.ConfigLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertiesReader {
    private static final ConfigLoader configLoader;

    static {
        try {
            configLoader = new ConfigLoader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getCountFromProperties() {
        return Integer.parseInt(configLoader.getCount(Config.DEALERS_COUNT));
    }

    public static Map<String, Integer> getValues() {
        Map<String, Config> configKeys = new HashMap<>();
        configKeys.put("DEALERS_COUNT", Config.DEALERS_COUNT);
        configKeys.put("WORKERS_COUNT", Config.WORKERS_COUNT);
        configKeys.put("ACCESSORY_COUNT", Config.ACCESSORY_COUNT);
        configKeys.put("ACCESSORY_TIME", Config.ACCESSORY_TIME);
        configKeys.put("BODY_TIME", Config.BODY_TIME);
        configKeys.put("ENGINE_TIME", Config.ENGINE_TIME);
        configKeys.put("AUTO_TIME", Config.AUTO_TIME);
        configKeys.put("ACCESSORY_LIMIT", Config.ACCESSORY_LIMIT);
        configKeys.put("BODY_LIMIT", Config.BODY_LIMIT);
        configKeys.put("ENGINE_LIMIT", Config.ENGINE_LIMIT);
        configKeys.put("AUTO_LIMIT", Config.AUTO_LIMIT);

        Map<String, Integer> configValues = new HashMap<>();
        configKeys.forEach((key, value) -> configValues.put(key, getCountFromProperties()));

        return configValues;
    }

    public enum Config {
        ACCESSORY_COUNT,
        DEALERS_COUNT,
        WORKERS_COUNT,
        ACCESSORY_TIME,
        BODY_TIME,
        ENGINE_TIME,
        AUTO_TIME,
        ACCESSORY_LIMIT,
        BODY_LIMIT,
        ENGINE_LIMIT,
        AUTO_LIMIT,
    }
}
