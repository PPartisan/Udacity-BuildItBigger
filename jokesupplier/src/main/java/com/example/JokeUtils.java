package com.example;

import java.util.List;

public final class JokeUtils {

    private JokeUtils() { throw new AssertionError(); }

    public static String toParagraph(Joke joke) {
        return toParagraph(joke.getLines());
    }

    public static String toParagraph(List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder(lines.size()*2);
        for (String line : lines) {
            builder.append(line);
            builder.append('\n');
        }
        if (builder.length() > 1 && builder.charAt(builder.length()-1) == '\n') {
            builder.deleteCharAt(builder.length()-1);
        }
        return builder.toString();
    }

}
