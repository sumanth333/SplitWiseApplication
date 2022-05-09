package com.application.splitwise.output;

import com.application.splitwise.model.SplitExpensesLog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ConsoleOutputWriterTest {

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
        SplitExpensesLog splitExpensesLog = new SplitExpensesLog("debtor", "beneficiary", 100.0);
        List<SplitExpensesLog> splitExpensesLogs = new ArrayList<>();
        splitExpensesLogs.add(splitExpensesLog);

        OutputWriter outputWriter = new ConsoleOutputWriter();
        outputWriter.writeSplitExpensesLog(splitExpensesLogs);

        assertEquals("debtor owes beneficiary Rs. 100.0", outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void shouldNotPrintAnythingForEmptyLog() {
        List<SplitExpensesLog> splitExpensesLogs = new ArrayList<>();

        OutputWriter outputWriter = new ConsoleOutputWriter();
        outputWriter.writeSplitExpensesLog(splitExpensesLogs);

        assertTrue(outputStreamCaptor.toString().isEmpty());
    }
}