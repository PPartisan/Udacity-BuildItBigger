package com.example;

import java.util.ArrayList;
import java.util.List;

final class KnockKnockJoke extends BaseJoke {

    private KnockKnockJoke(List<Line> lines, String... actorNames) {
        super(lines, actorNames);
    }

    static final class KnockKnockBuilder {

        List<Line> lines;
        String[] actorNames;

        private KnockKnockBuilder(String[] actorNames) {
            lines = new ArrayList<>();
            this.actorNames = actorNames;
        }

        static KnockKnockBuilder withActors(String... actorNames) {
            return new KnockKnockBuilder(actorNames).getBaseJoke();
        }

        KnockKnockBuilder who(String text) {

            if (text == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }

            addLine(Line.build(ACTOR_ONE, text));
            text = text.trim();

            if (text.endsWith(".")) {
                text = text.substring(0, text.length() - 1);
            }

            addLine(Line.build(ACTOR_TWO, text + " who?"));

            return this;
        }

        KnockKnockJoke punchLine(String text) {
            return punchLine(Line.build(ACTOR_ONE, text));
        }

        KnockKnockJoke punchLine(Line line) {
            if (line == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }
            addLine(line);
            return new KnockKnockJoke(lines, actorNames);
        }

        private KnockKnockBuilder addLine(Line line) {
            lines.add(line);
            return this;
        }

        private KnockKnockBuilder getBaseJoke() {
            addLine(Line.build(ACTOR_ONE, "Knock Knock."));
            addLine(Line.build(ACTOR_TWO, "Who's There?"));
            return this;
        }

    }

}
