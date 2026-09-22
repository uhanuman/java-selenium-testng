package com.demo.prep.tests;

import com.demo.prep.factory.DriverFactory;
import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

public class FactoryPatternTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void factoryReturnsSameThreadScopedDriverInstance() {
        WebDriver again = DriverFactory.getDriver();
        assertSame(driver, again);
    }

    @Test
    public void driverCanNavigateToTargetApplication() {
        driver.get(ConfigManager.getInstance().getBaseUrl());
        assertEquals(driver.getTitle(), "Swag Labs");
    }
}