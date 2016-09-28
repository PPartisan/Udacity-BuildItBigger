package com.example;

import java.util.List;

public final class JokeUtils {

    private JokeUtils() { throw new AssertionError(); }

    public static String toParagraphWithActors(Joke joke) {
        return toParagraphWithActors(joke, joke.getActorNames());
    }

    public static String toParagraphWithActors(Joke joke, String... actors) {

        if (joke == null || joke.getLines() == null || joke.getLines().isEmpty() ||
                actors.length < 1 || joke.getActorNames().length != actors.length) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        for (Line line : joke.getLines()) {
            builder.append(actors[line.actorId]);
            builder.append(": ");
            builder.append(line.text);
            builder.append('\n');
        }

        if (builder.length() > 1 && builder.charAt(builder.length()-1) == '\n') {
            builder.deleteCharAt(builder.length()-1);
        }

        return builder.toString();

    }

    public static String toParagraph(Joke joke) {
        if (joke == null) {
            return "";
        }
        return toParagraph(joke.getLines());
    }

    public static String toParagraph(List<Line> lines) {
        if (lines == null || lines.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Line line : lines) {
            builder.append(line.text);
            builder.append('\n');
        }
        if (builder.length() > 1 && builder.charAt(builder.length()-1) == '\n') {
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }

}
