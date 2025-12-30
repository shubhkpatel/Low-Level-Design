package org.nailyourinterview.lld.splitwise.strategy;

import org.nailyourinterview.lld.splitwise.model.Split;
import org.nailyourinterview.lld.splitwise.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata);
}