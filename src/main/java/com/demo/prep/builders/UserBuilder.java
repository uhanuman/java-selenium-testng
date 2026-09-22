package com.demo.prep.builders;

import com.demo.prep.models.User;
import com.demo.prep.singleton.ConfigManager;

public class UserBuilder {

    private static final String DEFAULT_FIRST_NAME = "John";
    private static final String DEFAULT_LAST_NAME = "Doe";
    private static final String DEFAULT_EMAIL = "john.doe@example.com";
    private static final String DEFAULT_ROLE = "buyer";

    private String username;
    private String password;
    private String firstName = DEFAULT_FIRST_NAME;
    private String lastName = DEFAULT_LAST_NAME;
    private String email;
    private String role = DEFAULT_ROLE;
    private String accessToken;

    private UserBuilder() {
    }

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public static UserBuilder aStandardUser() {
        ConfigManager config = ConfigManager.getInstance();
        return aUser()
                .withUsername(config.getStandardUsername())
                .withPassword(config.getStandardPassword())
                .withEmail("standard_user@example.com");
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public UserBuilder withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public User build() {
        return User.builder()
                .withUsername(username)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email == null ? DEFAULT_EMAIL : email)
                .withRole(role)
                .withAccessToken(accessToken)
                .build();
    }
}