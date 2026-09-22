package com.demo.prep.tests;

import com.demo.prep.actors.Actor;
import com.demo.prep.factory.DriverFactory;
import com.demo.prep.models.User;
import com.demo.prep.pages.HomePage;
import com.demo.prep.pages.LoginPage;
import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;
    protected ConfigManager config;
    protected Actor actor;

    @BeforeMethod
    public void setUpBase() {
        config = ConfigManager.getInstance();
        driver = DriverFactory.getDriver();
        actor = Actor.named("QA-Engineer").canUseWebBrowser(driver);
    }

    @AfterMethod
    public void tearDownBase() {
        DriverFactory.quitDriver();
    }

    protected HomePage loginAs(User user) {
        return new LoginPage(driver).open(config.getBaseUrl()).login(user);
    }
}