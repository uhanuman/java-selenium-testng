package com.demo.prep.tasks;

import com.demo.prep.actors.Actor;
import com.demo.prep.actors.Question;
import com.demo.prep.pages.HomePage;

public class VerifyWelcomeMessage implements Question<String> {

    private final HomePage homePage;

    private VerifyWelcomeMessage(HomePage homePage) {
        this.homePage = homePage;
    }

    public static VerifyWelcomeMessage displayedOn(HomePage homePage) {
        return new VerifyWelcomeMessage(homePage);
    }

    @Override
    public String answeredBy(Actor actor) {
        return homePage.getWelcomeMessage();
    }
}