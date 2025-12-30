package org.nailyourinterview.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User {
    private final String id;
    private final String name;
}