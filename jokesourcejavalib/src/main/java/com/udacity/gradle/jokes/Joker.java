package com.udacity.gradle.jokes;

import java.util.Random;

public class Joker {
    String joke[] = {
            "Between a teacher and a train.The teacher says spit out your gum and the train says chew chew chew.",
            "why didn't the two 4s want any lunch?.Because they already 8.",
            "Why did the teacher turn on the lights?.Because her class was so dim."
    };

    public String getJoke(){
        Random rm = new Random();
        return joke[rm.nextInt(joke.length)];
    }
}
