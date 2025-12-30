package org.nailyourinterview.lld.customer_support;

import org.nailyourinterview.lld.customer_support.enums.IssueStatus;
import org.nailyourinterview.lld.customer_support.enums.IssueType;
import org.nailyourinterview.lld.customer_support.model.Issue;
import org.nailyourinterview.lld.customer_support.repository.AgentRepository;
import org.nailyourinterview.lld.customer_support.repository.IssueRepository;
import org.nailyourinterview.lld.customer_support.service.AgentService;
import org.nailyourinterview.lld.customer_support.service.AssignmentService;
import org.nailyourinterview.lld.customer_support.service.IssueService;
import org.nailyourinterview.lld.customer_support.strategy.assignment.DefaultAssignmentStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        AgentRepository agentRepository = new AgentRepository();
        IssueRepository issueRepository = new IssueRepository();

        AgentService agentService = new AgentService(agentRepository);
        IssueService issueService = new IssueService(issueRepository, agentRepository);
        AssignmentService assignmentService = new AssignmentService(agentRepository, issueRepository, new DefaultAssignmentStrategy());

        Issue i1 = issueService.createIssue("T1", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser1@test.com");
        Issue i2 = issueService.createIssue("T2", IssueType.MUTUAL_FUND_RELATED, "Purchase Failed", "Unable to purchase Mutual Fund", "testUser2@test.com");
        Issue i3 = issueService.createIssue("T3", IssueType.PAYMENT_RELATED, "Payment Failed", "My payment failed but money is debited", "testUser2@test.com");

        agentService.addAgent("agent1@test.com", "Agent 1", Arrays.asList(IssueType.PAYMENT_RELATED, IssueType.GOLD_RELATED));
        agentService.addAgent("agent2@test.com", "Agent 2", Collections.singletonList(IssueType.PAYMENT_RELATED));

        assignmentService.assignIssue(i1.getId());
        assignmentService.assignIssue(i2.getId());
        assignmentService.assignIssue(i3.getId());

        System.out.println("\n--- Issues for testUser2@test.com ---");
        issueService.getIssues(Map.of("email", "testUser2@test.com"))
                .forEach(System.out::println);

        System.out.println("\n--- Payment Related Issues ---");
        issueService.getIssues(Map.of("type", "Payment Related"))
                .forEach(System.out::println);

        issueService.updateIssue(i3.getId(), IssueStatus.IN_PROGRESS, "Waiting for payment confirmation");

        issueService.resolveIssue(i3.getId(), "Payment failed. Debited amount will be reversed.");

        System.out.println("\n--- Agent Work History ---");
        agentService.viewAgentsWorkHistory();
    }
}