package com.application.splitwise.output;

import com.application.splitwise.model.Transaction;

import java.util.List;

public class ConsoleTransactionsWriter implements TransactionsWriter {
    @Override
    public void writeTransactions(List<Transaction> splitExpensesLogs) {
        for (Transaction transaction : splitExpensesLogs) {
            System.out.println(transaction.getPayer().getName() + " owes " +
                    transaction.getReceiver().getName() + " Rs. " + transaction.getAmount());
        }
    }
}
