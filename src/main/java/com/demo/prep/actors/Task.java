package com.demo.prep.actors;

@FunctionalInterface
public interface Task {

    void performAs(Actor actor);
}