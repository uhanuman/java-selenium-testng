package com.demo.prep.strategies;

import com.demo.prep.models.User;
import com.demo.prep.pages.LoginPage;
import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.WebDriver;

public class BasicAuthStrategy implements AuthenticationStrategy {

    private static final String DESCRIPTION = "Form-based username/password authentication";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean authenticate(WebDriver driver, User user) {
        String baseUrl = ConfigManager.getInstance().getBaseUrl();
        return new LoginPage(driver).open(baseUrl).login(user).isLoaded();
    }
}