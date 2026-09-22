package com.demo.prep.tests;

import com.demo.prep.singleton.ConfigManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

public class SingletonPatternTest {

    @Test
    public void configManagerReturnsSameInstance() {
        ConfigManager first = ConfigManager.getInstance();
        ConfigManager second = ConfigManager.getInstance();

        assertSame(first, second);
    }

    @Test
    public void configManagerExposesConfiguredValues() {
        ConfigManager config = ConfigManager.getInstance();

        assertEquals(config.getBaseUrl(), "https://www.saucedemo.com");
        assertEquals(config.getBrowser(), "chrome");
        assertTrue(config.getDefaultTimeoutSeconds() > 0);
    }
}