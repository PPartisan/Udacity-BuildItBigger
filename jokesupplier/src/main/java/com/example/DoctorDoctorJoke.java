package com.example;

import java.util.ArrayList;
import java.util.List;

final class DoctorDoctorJoke extends BaseJoke {

    private DoctorDoctorJoke(List<Line> lines, String[] actorNames) {
        super(lines, actorNames);
    }

    static final class DoctorDoctorBuilder {

        List<Line> lines;
        String[] actorNames;

        private DoctorDoctorBuilder(String[] actorNames) {
            lines = new ArrayList<>();
            this.actorNames = actorNames;
        }

        static DoctorDoctorBuilder withActors(String[] actorNames) {
            System.out.print("Actor Names length: " + actorNames.length);
            return new DoctorDoctorBuilder(actorNames).getBaseJoke();
        }

        DoctorDoctorBuilder openingLine(String text) {
            return openingLine(Line.build(ACTOR_ONE, text));
        }

        DoctorDoctorBuilder openingLine(Line line) {
            if (line == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }
            addToLine(line);
            return this;
        }

        DoctorDoctorBuilder addToLine(Line line) {
            if (line == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }

            final int lastIndex = lines.size() - 1;
            final Line lastEntry = lines.get(lastIndex);
            String lastEntryText = lastEntry.text;

            if (!lastEntryText.endsWith(" ")) {
                lastEntryText = lastEntryText + " ";
            }

            lastEntryText = lastEntryText + line.text;
            lines.set(lastIndex, Line.build(lastEntry.actorId, lastEntryText));

            return this;
        }

        DoctorDoctorBuilder addNewLine(Line line) {
            if (line == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }
            lines.add(line);
            return this;
        }

        DoctorDoctorJoke punchLine(String text) {
            return punchLine(Line.build(ACTOR_TWO, text));
        }

        DoctorDoctorJoke punchLine(Line line) {
            if (line == null) {
                throw new IllegalArgumentException("'addNewLine' must not be null");
            }
            lines.add(line);
            return new DoctorDoctorJoke(lines, actorNames);
        }

        private DoctorDoctorBuilder getBaseJoke() {
            return this.addNewLine(Line.build(ACTOR_ONE, "Doctor, Doctor!"));
        }

    }
}
