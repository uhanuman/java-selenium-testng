package com.demo.prep.pages;

import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By ORDER_CONFIRMATION = By.xpath("//h2[contains(text(),'Thank you for your order')]");

    private final WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage enterShippingDetails(String firstName, String lastName, String postalCode) {
        waitForVisible(FIRST_NAME).sendKeys(firstName);
        waitForVisible(LAST_NAME).sendKeys(lastName);
        waitForVisible(POSTAL_CODE).sendKeys(postalCode);
        waitForClickable(CONTINUE_BUTTON).click();
        return this;
    }

    public CheckoutPage finishOrder() {
        waitForClickable(FINISH_BUTTON).click();
        return this;
    }

    public String getOrderConfirmation() {
        return waitForVisible(ORDER_CONFIRMATION).getText();
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