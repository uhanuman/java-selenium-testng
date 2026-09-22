package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.models.User;
import com.demo.prep.pages.HomePage;
import com.demo.prep.strategies.BasicAuthStrategy;
import com.demo.prep.tasks.Login;
import com.demo.prep.tasks.Navigate;
import com.demo.prep.tasks.VerifyWelcomeMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ScreenplayPatternTest extends BaseTest {

    @Test
    public void actorCanNavigateLoginAndVerifyWelcomeMessage() {
        User user = UserBuilder.aStandardUser().build();

        actor.attemptsTo(
                Navigate.to(config.getBaseUrl()),
                Login.with(user, new BasicAuthStrategy()));

        String welcomeMessage = actor.asksFor(VerifyWelcomeMessage.displayedOn(new HomePage(driver)));

        assertEquals(welcomeMessage, "Products");
    }
}