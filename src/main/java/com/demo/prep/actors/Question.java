package com.demo.prep.actors;

@FunctionalInterface
public interface Question<T> {

    T answeredBy(Actor actor);
}