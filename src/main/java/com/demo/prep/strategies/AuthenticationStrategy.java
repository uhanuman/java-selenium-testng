package com.demo.prep.strategies;

import com.demo.prep.models.User;
import org.openqa.selenium.WebDriver;

public interface AuthenticationStrategy {

    String getDescription();

    boolean authenticate(WebDriver driver, User user);
}