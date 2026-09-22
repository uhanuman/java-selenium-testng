package com.demo.prep.actors;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Actor {

    private final String name;
    private final Map<String, Object> memory = new HashMap<>();
    private WebDriver webDriver;

    private Actor(String name) {
        this.name = name;
    }

    public static Actor named(String name) {
        return new Actor(name);
    }

    public Actor canUseWebBrowser(WebDriver webDriver) {
        this.webDriver = webDriver;
        return this;
    }

    public WebDriver webDriver() {
        return webDriver;
    }

    public Actor attemptsTo(Task... tasks) {
        for (Task task : tasks) {
            task.performAs(this);
        }
        return this;
    }

    public <T> T asksFor(Question<T> question) {
        return question.answeredBy(this);
    }

    public Actor remember(String key, Object value) {
        memory.put(key, value);
        return this;
    }

    public <T> T recall(String key) {
        return (T) memory.get(key);
    }

    public String getName() {
        return name;
    }
}