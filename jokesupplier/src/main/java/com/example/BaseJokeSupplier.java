package com.example;

import java.util.List;
import java.util.Random;

abstract class BaseJokeSupplier implements JokeSupplier {

    private List<Joke> jokes;
    private final Random random;

    BaseJokeSupplier() {
        random = new Random();
    }

    @Override
    public Joke getJoke() {
        return getJokes().get(random.nextInt(getJokeCount()));
    }

    @Override
    public Joke getJoke(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > getJokeCount()) {
            index = getJokeCount() - 1;
        }
        return getJokes().get(index);
    }

    @Override
    public List<Joke> getJokes() {
        if (jokes == null) {
            jokes = buildJokes();
        }
        return jokes;
    }

    @Override
    public int getJokeCount() {
        return getJokes().size();
    }

    abstract List<Joke> buildJokes();

}
