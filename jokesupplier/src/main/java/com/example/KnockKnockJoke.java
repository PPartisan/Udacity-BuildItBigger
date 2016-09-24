package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class KnockKnockJoke implements Joke {

    private final List<String> lines;

    private KnockKnockJoke(List<String> lines) {
        this.lines = Collections.unmodifiableList(lines);
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        if (lines == null) {
            return "";
        }
        return Arrays.toString(lines.toArray());
    }

    static final class KnockKnockBuilder {

        List<String> lines;

        private KnockKnockBuilder() {
            lines = new ArrayList<>();
        }

        static KnockKnockBuilder who(String line) {

            if (line == null) {
                throw new IllegalArgumentException("'line' must not be null");
            }

            KnockKnockBuilder builder = getBaseJoke();
            builder.addLine(line);
            line = line.trim();

            if (line.endsWith(".")) {
                line = line.substring(0, line.length() - 1);
            }

            builder.addLine(line + " who?");

            return builder;
        }

        KnockKnockJoke punchLine(String line) {
            if (line == null) {
                throw new IllegalArgumentException("'line' must not be null");
            }
            addLine(line);
            return new KnockKnockJoke(lines);
        }

        private KnockKnockBuilder addLine(String line) {
            lines.add(line);
            return this;
        }

        private static KnockKnockBuilder getBaseJoke() {
            return new KnockKnockBuilder().addLine("Knock Knock.").addLine("Who's There?");
        }

    }

}
