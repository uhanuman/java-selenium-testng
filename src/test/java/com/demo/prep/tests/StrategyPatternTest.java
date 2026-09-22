package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.models.User;
import com.demo.prep.strategies.BasicAuthStrategy;
import com.demo.prep.strategies.OAuthStrategy;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class StrategyPatternTest extends BaseTest {

    @Test
    public void basicAuthStrategyLogsInWithCredentials() {
        User user = UserBuilder.aStandardUser().build();

        boolean authenticated = new BasicAuthStrategy().authenticate(driver, user);

        assertTrue(authenticated);
    }

    @Test
    public void oAuthStrategyFallsBackToFormLoginWithoutToken() {
        User user = UserBuilder.aStandardUser().build();

        boolean authenticated = new OAuthStrategy().authenticate(driver, user);

        assertTrue(authenticated);
    }

    @Test
    public void oAuthStrategyRunsAndContinuesWhenTokenProvided() {
        User user = UserBuilder.aStandardUser().withAccessToken("mock-token").build();

        boolean authenticated = new OAuthStrategy().authenticate(driver, user);

        assertTrue(authenticated);
    }
}