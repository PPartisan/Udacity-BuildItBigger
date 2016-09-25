package com.example;

import java.util.List;

public interface Joke {

    int ACTOR_ONE = 0;
    int ACTOR_TWO = 1;
    int ACTOR_THREE = 2;
    int ACTOR_FOUR = 3;
    int ACTOR_FIVE = 4;

    List<Line> getLines();
    String[] getActorNames();

}
