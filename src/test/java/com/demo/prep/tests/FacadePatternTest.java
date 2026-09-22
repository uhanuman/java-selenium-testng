package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.facade.CheckoutFacade;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FacadePatternTest extends BaseTest {

    @Test
    public void checkoutFacadeCompletesPurchase() {
        loginAs(UserBuilder.aStandardUser().build());

        CheckoutFacade facade = new CheckoutFacade(driver);
        String confirmation = facade.purchase(CheckoutFacade.SAUCE_LABS_BACKPACK, "John", "Doe", "12345");

        assertTrue(confirmation.contains("Thank you for your order"));
    }
}