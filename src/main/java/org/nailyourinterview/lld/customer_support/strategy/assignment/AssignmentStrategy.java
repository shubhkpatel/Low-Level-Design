package org.nailyourinterview.lld.customer_support.strategy.assignment;

import org.nailyourinterview.lld.customer_support.model.Agent;
import org.nailyourinterview.lld.customer_support.model.Issue;

import java.util.List;

public interface AssignmentStrategy {
    Agent assign(List<Agent> agents, Issue issue);
}