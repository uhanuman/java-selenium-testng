package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.facade.CheckoutFacade;
import com.demo.prep.models.User;
import com.demo.prep.pages.HomePage;
import com.demo.prep.strategies.BasicAuthStrategy;
import com.demo.prep.tasks.Login;
import com.demo.prep.tasks.Navigate;
import com.demo.prep.tasks.VerifyWelcomeMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

public class AllPatternsIntegratedTest extends BaseTest {

    @Test
    public void allPatternsWorkTogetherForEndToEndPurchase() {
        User account = UserBuilder.aStandardUser().build();

        actor.attemptsTo(
                Navigate.to(config.getBaseUrl()),
                Login.with(account, new BasicAuthStrategy()));

        String welcomeMessage = actor.asksFor(VerifyWelcomeMessage.displayedOn(new HomePage(driver)));
        assertEquals(welcomeMessage, "Products");

        CheckoutFacade facade = new CheckoutFacade(driver);
        String confirmation = facade.purchase(CheckoutFacade.SAUCE_LABS_BACKPACK, "John", "Doe", "12345");

        assertTrue(confirmation.contains("Thank you for your order"));
        assertSame(actor.recall("current.user"), account);
    }
}