package com.example;

public final class Line {

    public final int actorId;
    public final String text;

    private Line(int actorId, String text) {
        this.actorId = actorId;
        this.text = text;
    }

    static Line build(int actorId, String text) {
        return new Line(actorId, text);
    }

}
