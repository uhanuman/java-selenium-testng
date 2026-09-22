package com.demo.prep.pages;

import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private static final By CHECKOUT_BUTTON = By.id("checkout");

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage clickCheckout() {
        new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getInstance().getDefaultTimeoutSeconds()))
                .until(ExpectedConditions.elementToBeClickable(CHECKOUT_BUTTON))
                .click();
        return new CheckoutPage(driver);
    }
}