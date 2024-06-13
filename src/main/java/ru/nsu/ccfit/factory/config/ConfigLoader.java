package ru.nsu.ccfit.factory.config;

import ru.nsu.ccfit.factory.PropertiesReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader() throws IOException {
        properties.load(new FileInputStream(Objects.requireNonNull(
                getClass().getClassLoader().getResource("application.properties")).getPath()));
    }

    public String getCount(PropertiesReader.Config config) {
        return properties.getProperty(config.name());
    }
}
