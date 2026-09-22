package com.demo.prep.tasks;

import com.demo.prep.actors.Actor;
import com.demo.prep.actors.Task;
import com.demo.prep.models.User;
import com.demo.prep.strategies.AuthenticationStrategy;

public class Login implements Task {

    private final User user;
    private final AuthenticationStrategy strategy;

    private Login(User user, AuthenticationStrategy strategy) {
        this.user = user;
        this.strategy = strategy;
    }

    public static Login with(User user, AuthenticationStrategy strategy) {
        return new Login(user, strategy);
    }

    @Override
    public void performAs(Actor actor) {
        strategy.authenticate(actor.webDriver(), user);
        actor.remember("current.user", user);
    }
}