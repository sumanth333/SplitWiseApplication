package com.application.splitwise.service;

import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Person;
import com.application.splitwise.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransactionsGeneratorTest {
    @BeforeEach
    void setUp() {
        PersonsShareService.getInstance().clearExistingShareDetails();
    }

    @Test
    void shouldReturnEmptyListOnProvidingEqualExpenditureOfPeople() {
        Person testPerson1 = new Person("James");
        Person testPerson2 = new Person("John");
        BigDecimal amount = new BigDecimal("100");
        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.addNewPersonShare(testPerson1, null);
        personsShareService.addNewPersonShare(testPerson2, null);

        List<Expenditure> expenditures = new ArrayList<>();
        Expenditure expenditure1 = new Expenditure(testPerson1, amount, ExpenditureStatus.UNSETTLED);
        Expenditure expenditure2 = new Expenditure(testPerson2, amount, ExpenditureStatus.UNSETTLED);
        expenditures.add(expenditure1);
        expenditures.add(expenditure2);

        TransactionsGenerator transactionsGenerator = new TransactionsGenerator();
        List<Transaction> transactions = transactionsGenerator.generateTransactions(expenditures);

        assertTrue(transactions.isEmpty());
    }

    @Test
    void shouldReturnEmptyListOnProvidingNoExpenditures() {
        List<Expenditure> expenditures = new ArrayList<>();

        TransactionsGenerator transactionsGenerator = new TransactionsGenerator();
        List<Transaction> transactions = transactionsGenerator.generateTransactions(expenditures);

        assertTrue(transactions.isEmpty());
    }

    @Test
    void shouldReturnValidTransactionsForGivenExpenditures() {
        Person testPerson1 = new Person("James");
        Person testPerson2 = new Person("John");
        BigDecimal amount1 = new BigDecimal("110");
        BigDecimal amount2 = new BigDecimal("80");
        Person testPerson3 = new Person("Jack");
        BigDecimal amount3 = new BigDecimal("70");
        Person testPerson4 = new Person("Jeff");
        BigDecimal amount4 = new BigDecimal("140");

        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.addNewPersonShare(testPerson1, null);
        personsShareService.addNewPersonShare(testPerson2, null);
        personsShareService.addNewPersonShare(testPerson3, null);
        personsShareService.addNewPersonShare(testPerson4, null);

        List<Expenditure> expenditures = new ArrayList<>();
        Expenditure expenditure1 = new Expenditure(testPerson1, amount1, ExpenditureStatus.UNSETTLED);
        Expenditure expenditure2 = new Expenditure(testPerson2, amount2, ExpenditureStatus.UNSETTLED);
        Expenditure expenditure3 = new Expenditure(testPerson3, amount3, ExpenditureStatus.UNSETTLED);
        Expenditure expenditure4 = new Expenditure(testPerson4, amount4, ExpenditureStatus.UNSETTLED);

        expenditures.add(expenditure1);
        expenditures.add(expenditure2);
        expenditures.add(expenditure3);
        expenditures.add(expenditure4);

        TransactionsGenerator transactionsGenerator = new TransactionsGenerator();
        List<Transaction> transactions = transactionsGenerator.generateTransactions(expenditures);

        assertEquals(3, transactions.size());
        assertEquals(testPerson2, transactions.get(0).getPayer());
        assertEquals(testPerson1, transactions.get(0).getReceiver());
        assertEquals(testPerson2, transactions.get(1).getPayer());
        assertEquals(testPerson4, transactions.get(1).getReceiver());
        assertEquals(testPerson3, transactions.get(2).getPayer());
        assertEquals(testPerson4, transactions.get(2).getReceiver());
    }

    @Test
    void shouldReturnValidTransactionsForGivenExpendituresWithShares() {
        Person testPerson1 = new Person("James");
        Person testPerson2 = new Person("John");
        BigDecimal amount1 = new BigDecimal("100");
        BigDecimal amount2 = new BigDecimal("40");

        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.addNewPersonShare(testPerson1, 0.6);
        personsShareService.addNewPersonShare(testPerson2, 0.4);

        List<Expenditure> expenditures = new ArrayList<>();
        Expenditure expenditure1 = new Expenditure(testPerson1, amount1, ExpenditureStatus.UNSETTLED);
        Expenditure expenditure2 = new Expenditure(testPerson2, amount2, ExpenditureStatus.UNSETTLED);

        expenditures.add(expenditure1);
        expenditures.add(expenditure2);

        TransactionsGenerator transactionsGenerator = new TransactionsGenerator();
        List<Transaction> transactions = transactionsGenerator.generateTransactions(expenditures);

        assertEquals(1, transactions.size());
        assertEquals(new BigDecimal("16.00"), transactions.get(0).getAmount());
    }
}