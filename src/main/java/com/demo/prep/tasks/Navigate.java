package com.demo.prep.tasks;

import com.demo.prep.actors.Actor;
import com.demo.prep.actors.Task;

public class Navigate implements Task {

    private final String url;

    private Navigate(String url) {
        this.url = url;
    }

    public static Navigate to(String url) {
        return new Navigate(url);
    }

    @Override
    public void performAs(Actor actor) {
        actor.webDriver().get(url);
    }
}