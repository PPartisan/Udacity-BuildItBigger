package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class DoctorDoctorJoke implements Joke {

    private final List<String> lines;

    private DoctorDoctorJoke(List<String> lines) {
        this.lines = Collections.unmodifiableList(lines);
    }

    @Override
    public List<String> getLines() {
        return lines;
    }

    static final class DoctorDoctorBuilder {

        List<String> lines;

        private DoctorDoctorBuilder() {
            lines = new ArrayList<>();
        }

        static DoctorDoctorBuilder openingLine(String line) {
            if (line == null) {
                throw new IllegalArgumentException("'line' must not be null");
            }
            return getBaseJoke().line(line);
        }

        DoctorDoctorBuilder line(String line) {
            if (line == null) {
                throw new IllegalArgumentException("'line' must not be null");
            }
            lines.add(line);
            return this;
        }

        DoctorDoctorJoke punchLine(String line) {
            if (line == null) {
                throw new IllegalArgumentException("'line' must not be null");
            }
            lines.add(line);
            return new DoctorDoctorJoke(lines);
        }

        private static DoctorDoctorBuilder getBaseJoke() {
            return new DoctorDoctorBuilder().line("Doctor, Doctor!");
        }

    }
}
