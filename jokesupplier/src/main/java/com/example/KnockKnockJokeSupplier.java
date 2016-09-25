package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.KnockKnockJoke.KnockKnockBuilder.withActors;

final class KnockKnockJokeSupplier extends BaseJokeSupplier {

    private static final String[] DEFAULT_ACTOR_NAMES = new String[] { "Person One", "Person Two" };

    KnockKnockJokeSupplier(){
        super();
    }

    @Override
    List<Joke> buildJokes() {

        List<Joke> knockKnockJokes = new ArrayList<>();

        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Olive.").punchLine("Olive you!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Carmen.").punchLine("Carmen let me in already!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Ya.").punchLine("I'm excited to see you too!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Sherlock.").punchLine("Sherlock your door tight!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Robin.").punchLine("Robin you! Hand over your cash!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Police.").punchLine("Police hurry - it's freezing out here!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Orange.").punchLine("Orange you gonna open the door?"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES).
                who("Needle.").punchLine("Needle little help getting the door!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Nana.").punchLine("Nana your business!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Luke").punchLine("Luke through the keyhole and see!"));
        knockKnockJokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .who("Lettuce").punchLine("Lettuce in already!"));

        return Collections.unmodifiableList(knockKnockJokes);
    }

}
