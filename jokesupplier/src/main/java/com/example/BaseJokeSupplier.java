package com.example;

import java.util.List;
import java.util.Random;

abstract class BaseJokeSupplier implements JokeSupplier {

    private final List<Joke> jokes;
    private final Random random;

    BaseJokeSupplier() {
        jokes = buildJokes();
        random = new Random();
    }

    @Override
    public Joke getJoke() {
        return jokes.get(random.nextInt(getJokeCount()));
    }

    @Override
    public Joke getJoke(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > getJokeCount()) {
            index = getJokeCount() - 1;
        }
        return jokes.get(index);
    }

    @Override
    public List<Joke> getJokes() {
        return jokes;
    }

    @Override
    public int getJokeCount() {
        return jokes.size();
    }

    abstract List<Joke> buildJokes();

}
