package org.nailyourinterview.lld.snakes_and_ladder.model;

import org.nailyourinterview.lld.snakes_and_ladder.enums.ObstacleType;

public class Ladder extends Obstacle {
    public Ladder(int top, int bottom) {
        super(bottom, top);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.LADDER;
    }
}