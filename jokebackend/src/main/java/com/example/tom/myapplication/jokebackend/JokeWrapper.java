package com.example.tom.myapplication.jokebackend;

import com.example.Joke;
import com.example.JokeUtils;

public class JokeWrapper {

    private final Joke mJoke;
    private String[] mActors;

    public JokeWrapper(Joke joke, String[] actors) {
        mJoke = joke;
        mActors = actors;
    }

    public void setActors(String[] actors) {
        mActors = actors;
    }

    public String getParagraphedJoke() {
        return JokeUtils.toParagraph(mJoke);
    }

    public String getParagraphedJokeWithActors() {
        String joke;
        if (mActors == null) {
            joke = JokeUtils.toParagraphWithActors(mJoke);
        } else {
            joke = JokeUtils.toParagraphWithActors(mJoke, getUserGeneratedActors());
        }
        return joke;
    }

    private String[] getUserGeneratedActors() {
        final int jokeActorsLength = mJoke.getActorNames().length;
        final int userActorsLength = mActors.length;

        final String[] actors = new String[jokeActorsLength];

        for (int i = 0; i < jokeActorsLength; i++) {
            final String actor = (i < userActorsLength) ? mActors[i] : mJoke.getActorNames()[i];
            actors[i] = actor;
        }

        return actors;

    }

}
