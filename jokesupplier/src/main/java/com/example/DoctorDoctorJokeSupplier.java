package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.DoctorDoctorJoke.DoctorDoctorBuilder.openingLine;

public final class DoctorDoctorJokeSupplier implements JokeSupplier {

    private final List<Joke> doctorDoctorJokes;
    private final Random random;

    public DoctorDoctorJokeSupplier() {
        doctorDoctorJokes = buildJokes();
        random = new Random();
    }

    @Override
    public Joke getJoke() {
        return doctorDoctorJokes.get(random.nextInt(getJokeCount()));
    }

    @Override
    public Joke getJoke(int index) {
        if (index < 0) {
            index = 0;
        } else if (index > getJokeCount()) {
            index = getJokeCount() - 1;
        }
        return doctorDoctorJokes.get(index);
    }

    @Override
    public List<? extends Joke> getJokes() {
        return doctorDoctorJokes;
    }

    @Override
    public int getJokeCount() {
        return doctorDoctorJokes.size();
    }

    private List<Joke> buildJokes() {

        List<Joke> jokes = new ArrayList<>();

        jokes.add(
                openingLine("Some days I feel like a tepee, and others like a wig-wam!")
                .punchLine("You're two tents.")
        );
        jokes.add(
                openingLine("I have a split personality.")
                .punchLine("Then you'd both better sit down.")
        );
        jokes.add(
                openingLine("I keep seeing double.")
                .line("You'd better sit down on the couch.")
                .punchLine("Which one?"))
        ;
        jokes.add(
                openingLine("I keep thinking I'm a frog")
                .line("And what's wrong with that?")
                .punchLine("I think I'm going to croak!")
        );
        jokes.add(
                openingLine("I think I'm a vampire!")
                .punchLine("Necks please!")
        );
        jokes.add(
                openingLine("I feel like a pair of curtains!")
                .punchLine("Pull yourself together!")
        );
        jokes.add(
                openingLine("My son swallowed a roll of film!")
                .punchLine("Let's hope nothing develops!")
        );

        return jokes;
    }

}
