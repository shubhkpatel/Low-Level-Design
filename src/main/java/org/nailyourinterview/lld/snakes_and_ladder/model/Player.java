package org.nailyourinterview.lld.snakes_and_ladder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Player {
    private final String name;
    private int position = 1;
}
