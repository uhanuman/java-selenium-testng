package com.demo.prep.pages;

import com.demo.prep.models.User;
import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final By USERNAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage open(String url) {
        driver.get(url);
        waitForVisible(USERNAME_FIELD);
        return this;
    }

    public HomePage login(User user) {
        waitForVisible(USERNAME_FIELD).sendKeys(user.getUsername());
        waitForVisible(PASSWORD_FIELD).sendKeys(user.getPassword());
        waitForClickable(LOGIN_BUTTON).click();
        return new HomePage(driver);
    }

    public boolean isDisplayed() {
        try {
            waitForVisible(LOGIN_BUTTON);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElements(ERROR_MESSAGE).stream().anyMatch(WebElement::isDisplayed);
    }

    public String getErrorMessage() {
        return waitForVisible(ERROR_MESSAGE).getText();
    }

    private WebElement waitForVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInstance().getDefaultTimeoutSeconds()))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInstance().getDefaultTimeoutSeconds()))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}