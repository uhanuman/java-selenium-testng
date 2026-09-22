package com.demo.prep.facade;

import com.demo.prep.pages.CheckoutPage;
import com.demo.prep.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class CheckoutFacade {

    public static final String SAUCE_LABS_BACKPACK = "sauce-labs-backpack";

    private final WebDriver driver;
    private final HomePage homePage;

    public CheckoutFacade(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
    }

    public String purchase(String productId, String firstName, String lastName, String postalCode) {
        homePage.addToCart(productId)
                .openCart()
                .clickCheckout()
                .enterShippingDetails(firstName, lastName, postalCode)
                .finishOrder();
        return new CheckoutPage(driver).getOrderConfirmation();
    }
}