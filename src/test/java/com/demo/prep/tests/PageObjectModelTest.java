package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.models.User;
import com.demo.prep.pages.HomePage;
import com.demo.prep.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PageObjectModelTest extends BaseTest {

    @Test
    public void shouldLoginAndSeeWelcomeMessage() {
        User user = UserBuilder.aStandardUser().build();

        HomePage home = new LoginPage(driver).open(config.getBaseUrl()).login(user);

        assertTrue(home.isLoaded());
        assertEquals(home.getWelcomeMessage(), "Products");
    }

    @Test
    public void shouldLogoutAndReturnToLoginPage() {
        loginAs(UserBuilder.aStandardUser().build());

        LoginPage loginPage = new HomePage(driver).logout();

        assertTrue(loginPage.isDisplayed());
    }
}