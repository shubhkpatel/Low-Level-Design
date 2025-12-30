package org.nailyourinterview.lld.splitwise.service;

import lombok.AllArgsConstructor;
import org.nailyourinterview.lld.splitwise.enums.SplitType;
import org.nailyourinterview.lld.splitwise.model.Group;
import org.nailyourinterview.lld.splitwise.model.User;
import org.nailyourinterview.lld.splitwise.repository.GroupRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class GroupService {

    private final GroupRepository repo;
    private final ExpenseService expenseService;
    private final DebtSimplificationService simplifier;

    public String createGroup(String name, List<User> members) {
        String id = UUID.randomUUID().toString();
        Group g = new Group(id, name);
        members.forEach(g::addMember);

        repo.save(g);
        return id;
    }

    public void addMember(String groupId, User user) {
        get(groupId).addMember(user);
    }

    public void addExpense(String groupId,
                           String description, double amount,
                           User paidBy, List<User> participants,
                           SplitType splitType, Map<User, Double> meta) {

        expenseService.addExpense(get(groupId), description, amount,
                paidBy, participants, splitType, meta);
    }

    public void simplifyDebts(String groupId) {
        simplifier.simplifyDebts(get(groupId));
    }

    public void printBalances(String groupId) {
        Group g = get(groupId);
        g.getMembers().forEach(u -> {
            var sheet = g.getBalanceSheet(u);

            double owe = 0, get = 0;
            for (double v : sheet.getBalances().values()) {
                if (v < 0) owe += -v; else get += v;
            }
            System.out.printf("""
                               💵 %s
                               Paid: %.2f  Expense: %.2f
                               You owe: %.2f, You get: %.2f%n""",
                    u.getName(), sheet.getTotalPaid(),
                    sheet.getTotalExpense(), owe, get);

            sheet.getBalances().forEach((other, val) -> System.out.printf(
                    "  %s %.2f %s%n",
                    val > 0 ? "← get" : "→ owe",
                    Math.abs(val),
                    other.getName()));
            System.out.println("--------------------------");
        });
    }

    private Group get(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + id));
    }
}