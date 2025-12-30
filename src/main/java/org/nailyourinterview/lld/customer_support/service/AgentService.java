package org.nailyourinterview.lld.customer_support.service;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.customer_support.enums.IssueType;
import org.nailyourinterview.lld.customer_support.model.Agent;
import org.nailyourinterview.lld.customer_support.model.Issue;
import org.nailyourinterview.lld.customer_support.repository.AgentRepository;

import java.util.*;

@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;

    public void addAgent(String email, String name, List<IssueType> issueTypes) {
        String id = "A" + UUID.randomUUID().toString().substring(0, 6);
        Agent agent = new Agent(id, email, name, new HashSet<>(issueTypes));
        agentRepository.save(agent);
        System.out.println(">>> Agent " + id + " created");
    }

    public void viewAgentsWorkHistory() {
        for (Agent agent : agentRepository.getAll()) {
            System.out.println(agent.getId() + " -> " + agent.getHistory());
        }
    }
}