package com.demo.prep.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BASE_URL = "https://www.saucedemo.com";
    private static final String DEFAULT_API_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final int DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int DEFAULT_RETRY_COUNT = 1;

    private static final ConfigManager INSTANCE = new ConfigManager();

    private final Properties properties;

    private ConfigManager() {
        this.properties = new Properties();
        loadDefaults();
        loadPropertiesFile();
    }

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getApiBaseUrl() {
        return getProperty("api.base.url");
    }

    public int getDefaultTimeoutSeconds() {
        return Integer.parseInt(getProperty("default.timeout.seconds"));
    }

    public int getRetryCount() {
        return Integer.parseInt(getProperty("retry.count"));
    }

    public boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public String getStandardUsername() {
        return getProperty("standard.user.username");
    }

    public String getStandardPassword() {
        return getProperty("standard.user.password");
    }

    private void loadDefaults() {
        properties.setProperty("browser", DEFAULT_BROWSER);
        properties.setProperty("base.url", DEFAULT_BASE_URL);
        properties.setProperty("api.base.url", DEFAULT_API_BASE_URL);
        properties.setProperty("default.timeout.seconds", String.valueOf(DEFAULT_TIMEOUT_SECONDS));
        properties.setProperty("retry.count", String.valueOf(DEFAULT_RETRY_COUNT));
        properties.setProperty("headless", Boolean.FALSE.toString());
    }

    private void loadPropertiesFile() {
        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
    }
}