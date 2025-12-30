package org.nailyourinterview.lld.splitwise.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BalanceSheet {
    private double totalPaid = 0.0;
    private double totalExpense = 0.0;
    private final Map<User, Double> balances = new HashMap<>();

    public void addTotalPaid(double amount) {
        this.totalPaid += amount;
    }

    public void addTotalExpense(double amount) {
        this.totalExpense += amount;
    }

    public void addBalance(User other, double amount) {
        balances.put(other, balances.getOrDefault(other, 0.0) + amount);
        if (Math.abs(balances.get(other)) < 1e-6) balances.remove(other);
    }

    public void clearBalances() {
        balances.clear();
    }

    public void print(User me) {
        double youOwe = 0.0, youGetBack = 0.0;
        for (double amount : balances.values()) {
            if (amount < 0) youOwe += -amount;
            else youGetBack += amount;
        }

        System.out.println("💵 Balance sheet of : " + me.getName());
        System.out.println("Total You Paid : " + totalPaid);
        System.out.println("Total Expense : " + totalExpense);
        System.out.println("Total You Owe : " + youOwe);
        System.out.println("Total You Get Back : " + youGetBack);

        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            double amount = entry.getValue();
            if (amount > 0)
                System.out.println("You get back " + amount + " from " + entry.getKey().getName());
            else if (amount < 0)
                System.out.println("You owe " + (-amount) + " to " + entry.getKey().getName());
        }
        System.out.println("---------------------------------");
        System.out.println("---------------------------------");
    }
}
