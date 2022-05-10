package com.application.splitwise.output;

import com.application.splitwise.model.Transaction;

import java.util.List;

public interface TransactionsWriter {
    void writeTransactions(List<Transaction> transactions);
}
