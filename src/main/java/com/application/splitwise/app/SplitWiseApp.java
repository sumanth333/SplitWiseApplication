package com.application.splitwise.app;

import com.application.splitwise.input.ConsoleExpenditureReader;
import com.application.splitwise.input.ExpenditureReader;
import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.Transaction;
import com.application.splitwise.output.ConsoleTransactionsWriter;
import com.application.splitwise.output.TransactionsWriter;
import com.application.splitwise.service.TransactionsGenerator;
import lombok.Setter;

import java.util.List;

@Setter
public class SplitWiseApp {
    private ExpenditureReader reader;
    private TransactionsGenerator transactionsGenerator;
    private TransactionsWriter writer;

    public SplitWiseApp() {
        reader = new ConsoleExpenditureReader();
        writer = new ConsoleTransactionsWriter();
        transactionsGenerator = new TransactionsGenerator();
    }

    public void run() {
        List<Expenditure> expenditures = reader.readExpenditures();
        List<Transaction> transactions = transactionsGenerator.generateTransactions(expenditures);
        writer.writeTransactions(transactions);
    }

}
