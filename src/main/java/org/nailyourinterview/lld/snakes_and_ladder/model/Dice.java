package org.nailyourinterview.lld.snakes_and_ladder.model;

import java.util.Random;

public class Dice {
    private final int noOfDices;
    private final Random random = new Random();

    public Dice(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    public int roll() {
        int sum = 0;
        for (int i = 0; i < noOfDices; i++) {
            sum += random.nextInt(6) + 1;
        }
        return sum;
    }
}
