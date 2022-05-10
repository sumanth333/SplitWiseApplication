package com.application.splitwise.input;

import com.application.splitwise.model.Expenditure;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleInputReaderTest {

    @Test
    void shouldReadExpendituresAndReturnValidExpenditures() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1,100\nPerson2,50\n\n".getBytes()));

        ConsoleExpenditureReader reader = new ConsoleExpenditureReader();
        System.setIn(stdin);

        List<Expenditure> expenditures = reader.readExpenditures();
        assertEquals(2, expenditures.size());
        assertEquals("Person1", expenditures.get(0).getPerson().getName());
        assertEquals(new BigDecimal("100"), expenditures.get(0).getAmount());

    }

    @Test
    void shouldAcceptMultipleExpenditureOfSamePerson() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1,100\nPerson1,20\nPerson2,60\nexit\n".getBytes()));

        ConsoleExpenditureReader reader = new ConsoleExpenditureReader();
        System.setIn(stdin);

        List<Expenditure> expenditures = reader.readExpenditures();
        assertEquals(2, expenditures.size());
        assertEquals(new BigDecimal("120"), expenditures.get(0).getAmount());
    }

    @Test
    void shouldReturnEmptyListFormAnInvalidInputFormat() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1 100\n".getBytes()));

        ConsoleExpenditureReader reader = new ConsoleExpenditureReader();
        System.setIn(stdin);

        List<Expenditure> expenditures = reader.readExpenditures();
        assertTrue(expenditures.isEmpty());
    }

    @Test
    void shouldReturnEmptyListFormAnInvalidInputValues() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1,expenditure\n\n".getBytes()));

        ConsoleExpenditureReader reader = new ConsoleExpenditureReader();
        System.setIn(stdin);

        List<Expenditure> expenditures = reader.readExpenditures();
        assertTrue(expenditures.isEmpty());
    }

}