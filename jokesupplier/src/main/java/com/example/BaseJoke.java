package com.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

abstract class BaseJoke implements Joke {

    private final List<Line> lines;
    private final String[] actorNames;

    BaseJoke(List<Line> lines, String[] actorNames) {
        this.lines = Collections.unmodifiableList(lines);
        this.actorNames = actorNames;
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    @Override
    public String[] getActorNames() {
        return actorNames;
    }

    @Override
    public String toString() {
        if (lines == null) {
            return "";
        }
        return Arrays.toString(lines.toArray());
    }

}
