package org.nailyourinterview.lld.snakes_and_ladder.factory;

import org.nailyourinterview.lld.snakes_and_ladder.model.Ladder;
import org.nailyourinterview.lld.snakes_and_ladder.model.Obstacle;
import org.nailyourinterview.lld.snakes_and_ladder.model.Snake;
import org.nailyourinterview.lld.snakes_and_ladder.enums.ObstacleType;

public class ObstacleFactory {
    public static Obstacle createObstacle(ObstacleType type, int up, int down) {
        return switch (type) {
            case SNAKE -> new Snake(up, down);
            case LADDER -> new Ladder(up, down);
            default -> throw new IllegalArgumentException("Invalid obstacle type");
        };
    }
}