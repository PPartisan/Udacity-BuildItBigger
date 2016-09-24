package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.KnockKnockJoke.KnockKnockBuilder.who;

public final class KnockKnockJokeSupplier implements JokeSupplier {

    private final List<Joke> knockKnockJokes;
    private final Random random;

    public KnockKnockJokeSupplier() {
        knockKnockJokes = buildJokes();
        random = new Random();
    }

    @Override
    public Joke getJoke() {
        return knockKnockJokes.get(random.nextInt(getJokeCount()));
    }

    @Override
    public Joke getJoke(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > getJokeCount()) {
            index = getJokeCount() - 1;
        }
        return knockKnockJokes.get(index);
    }

    @Override
    public List<? extends Joke> getJokes() {
        return knockKnockJokes;
    }

    @Override
    public int getJokeCount() {
        return knockKnockJokes.size();
    }

    private List<Joke> buildJokes() {

        List<Joke> knockKnockJokes = new ArrayList<>();

        knockKnockJokes.add(who("Olive.").punchLine("Olive you!"));
        knockKnockJokes.add(who("Carmen.").punchLine("Carmen let me in already!"));
        knockKnockJokes.add(who("Ya.").punchLine("I'm excited to see you too!"));
        knockKnockJokes.add(who("Sherlock.").punchLine("Sherlock your door tight!"));
        knockKnockJokes.add(who("Robin.").punchLine("Robin you! Hand over your cash!"));
        knockKnockJokes.add(who("Police.").punchLine("Police hurry - it's freezing out here!"));
        knockKnockJokes.add(who("Orange.").punchLine("Orange you gonna open the door?"));
        knockKnockJokes.add(who("Needle.").punchLine("Needle little help getting the door!"));
        knockKnockJokes.add(who("Nana.").punchLine("Nana your business!"));
        knockKnockJokes.add(who("Luke").punchLine("Luke through the keyhole and see!"));
        knockKnockJokes.add(who("Lettuce").punchLine("Lettuce in already!"));

        return Collections.unmodifiableList(knockKnockJokes);
    }

}
