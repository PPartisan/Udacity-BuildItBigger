package com.example;

import java.util.ArrayList;
import java.util.List;

import static com.example.DoctorDoctorJoke.DoctorDoctorBuilder.withActors;
import static com.example.Joke.ACTOR_ONE;

final class DoctorDoctorJokeSupplier extends BaseJokeSupplier {

    private static final String[] DEFAULT_ACTOR_NAMES = new String[] { "Patient", "Doctor" };

    DoctorDoctorJokeSupplier() {
        super();
    }

    @Override
    List<Joke> buildJokes() {

        List<Joke> jokes = new ArrayList<>();

        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("Some days I feel like a tepee, and others like a wig-wam!")
                .punchLine("You're two tents.")
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("I have a split personality.")
                .punchLine("Then you'd both better sit down.")
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("I keep seeing double.")
                .addNewLine(Line.build(Joke.ACTOR_TWO, "You'd better sit down on the couch."))
                .punchLine(Line.build(Joke.ACTOR_ONE, "Which one?"))
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("I keep thinking I'm a frog")
                .addNewLine(Line.build(Joke.ACTOR_TWO, "And what's wrong with that?"))
                .punchLine(Line.build(ACTOR_ONE, "I think I'm going to croak!"))
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("I think I'm a vampire!")
                .punchLine("Necks please!")
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("I feel like a pair of curtains!")
                .punchLine("Pull yourself together!")
        );
        jokes.add(withActors(DEFAULT_ACTOR_NAMES)
                .openingLine("My son swallowed a roll of film!")
                .punchLine("Let's hope nothing develops!")
        );

        return jokes;
    }

}
