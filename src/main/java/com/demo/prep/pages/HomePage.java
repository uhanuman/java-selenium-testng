package com.demo.prep.pages;

import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private static final By PAGE_TITLE = By.cssSelector("span.title");
    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By CART_LINK = By.cssSelector("a.shopping_cart_link");

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.findElements(INVENTORY_CONTAINER).stream().anyMatch(WebElement::isDisplayed);
    }

    public String getWelcomeMessage() {
        return waitForVisible(PAGE_TITLE).getText();
    }

    public HomePage addToCart(String productId) {
        waitForClickable(By.id("add-to-cart-" + productId)).click();
        return this;
    }

    public CartPage openCart() {
        waitForClickable(CART_LINK).click();
        return new CartPage(driver);
    }

    public LoginPage logout() {
        waitForClickable(MENU_BUTTON).click();
        waitForClickable(LOGOUT_LINK).click();
        return new LoginPage(driver);
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