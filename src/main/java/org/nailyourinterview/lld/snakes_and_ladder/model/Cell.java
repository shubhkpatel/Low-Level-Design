package org.nailyourinterview.lld.snakes_and_ladder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Cell {
    private final int position;
    private Obstacle obstacle;

    public boolean hasObstacle() {
        return obstacle != null;
    }

    public int getFinalPosition() {
        return hasObstacle() ? obstacle.movePlayer() : position;
    }
}