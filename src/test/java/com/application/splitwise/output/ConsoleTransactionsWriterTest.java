package com.application.splitwise.output;

import com.application.splitwise.model.Person;
import com.application.splitwise.model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ConsoleTransactionsWriterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void shouldBeAbleToPrintValidOutPutForGivenLogs() {
        Person testDebtor = new Person("debtor");
        Person testBeneficiary = new Person("beneficiary");
        BigDecimal testAmount = new BigDecimal("100.0");

        Transaction dummyTransaction = new Transaction(testDebtor, testBeneficiary, testAmount);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(dummyTransaction);

        TransactionsWriter transactionsWriter = new ConsoleTransactionsWriter();
        transactionsWriter.writeTransactions(transactions);

        assertEquals("debtor owes beneficiary Rs. 100.0", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void shouldNotPrintAnythingForEmptyLog() {
        List<Transaction> transactions = new ArrayList<>();

        TransactionsWriter transactionsWriter = new ConsoleTransactionsWriter();
        transactionsWriter.writeTransactions(transactions);

        assertTrue(outputStreamCaptor.toString().isEmpty());
    }
}