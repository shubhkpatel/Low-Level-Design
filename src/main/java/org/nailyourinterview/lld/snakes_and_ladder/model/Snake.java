package org.nailyourinterview.lld.snakes_and_ladder.model;

import org.nailyourinterview.lld.snakes_and_ladder.enums.ObstacleType;

public class Snake extends Obstacle {
    public Snake(int head, int tail) {
        super(head, tail);
    }

    @Override
    public ObstacleType getObstacleType() {
        return ObstacleType.SNAKE;
    }
}