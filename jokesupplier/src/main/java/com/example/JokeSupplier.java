package com.example;

import java.util.List;

public interface JokeSupplier {
    Joke getJoke();
    Joke getJoke(int index);
    List<? extends Joke> getJokes();
    int getJokeCount();
}
