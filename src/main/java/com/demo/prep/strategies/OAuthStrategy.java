package com.demo.prep.strategies;

import com.demo.prep.models.User;
import com.demo.prep.pages.HomePage;
import com.demo.prep.singleton.ConfigManager;
import org.openqa.selenium.WebDriver;

public class OAuthStrategy implements AuthenticationStrategy {

    private static final String DESCRIPTION = "Token-based OAuth authentication";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean authenticate(WebDriver driver, User user) {
        if (user.getAccessToken() != null) {
            String tokenUrl = ConfigManager.getInstance().getBaseUrl() + "?token=" + user.getAccessToken();
            driver.get(tokenUrl);
            if (new HomePage(driver).isLoaded()) {
                return true;
            }
        }
        return new BasicAuthStrategy().authenticate(driver, user);
    }
}