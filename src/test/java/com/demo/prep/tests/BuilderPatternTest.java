package com.demo.prep.tests;

import com.demo.prep.builders.UserBuilder;
import com.demo.prep.models.User;
import com.demo.prep.singleton.ConfigManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class BuilderPatternTest {

    @Test
    public void builderCreatesUserWithProvidedValues() {
        User user = UserBuilder.aUser()
                .withUsername("alice")
                .withPassword("p@ss")
                .withEmail("alice@example.com")
                .withRole("admin")
                .build();

        assertEquals(user.getUsername(), "alice");
        assertEquals(user.getPassword(), "p@ss");
        assertEquals(user.getEmail(), "alice@example.com");
        assertEquals(user.getRole(), "admin");
        assertNull(user.getAccessToken());
    }

    @Test
    public void builderAppliesDefaultsForUnsetFields() {
        User user = UserBuilder.aUser().withUsername("bob").withPassword("pw").build();

        assertEquals(user.getFirstName(), "John");
        assertEquals(user.getLastName(), "Doe");
        assertEquals(user.getRole(), "buyer");
    }

    @Test
    public void standardUserBuilderReadsCredentialsFromConfig() {
        ConfigManager config = ConfigManager.getInstance();

        User user = UserBuilder.aStandardUser().build();

        assertEquals(user.getUsername(), config.getStandardUsername());
        assertEquals(user.getPassword(), config.getStandardPassword());
    }
}