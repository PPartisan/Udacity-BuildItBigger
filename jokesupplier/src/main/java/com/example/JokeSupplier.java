package com.example;

import java.util.List;

public interface JokeSupplier {

    Joke getJoke();
    Joke getJoke(int index);
    List<Joke> getJokes();
    int getJokeCount();

    final class Factory {

        private Factory() { throw new AssertionError(); }

        public static JokeSupplier getJokeSupplier(JokeCategory category) {
            return category.supplier;
        }

    }

    enum JokeCategory {

        KNOCK_KNOCK(new KnockKnockJokeSupplier()), DOCTOR_DOCTOR(new DoctorDoctorJokeSupplier());

        JokeSupplier supplier;

        JokeCategory(JokeSupplier supplier) {
            this.supplier = supplier;
        }

    }

}
