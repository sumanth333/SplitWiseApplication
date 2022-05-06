package com.application.splitwise.input;

import com.application.splitwise.model.Person;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleInputReaderTest {

    @Test
    void shouldReadPersonExpenditureAndBuildPersonsList() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1,100\nPerson2,50\n\n".getBytes()));

        ConsoleInputReader reader = new ConsoleInputReader();
        System.setIn(stdin);

        List<Person> personsList = reader.readPersonsExpenditure();
        assertEquals(2, personsList.size());
    }

    @Test
    void shouldInstantiateNecessaryAttributes() {
        ConsoleInputReader reader = new ConsoleInputReader();
        assertNotNull(reader.getInputReader());
    }

    @Test
    void shouldThrowAnExceptionWhenInputFormatIsIncorrect() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1 100\n\n".getBytes()));

        ConsoleInputReader reader = new ConsoleInputReader();
        System.setIn(stdin);

        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                reader::readPersonsExpenditure
        );
    }

    @Test
    void shouldThrowAnExceptionIfInputValuesAreIncorrect() {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("Person1,expenditure\n\n".getBytes()));

        ConsoleInputReader reader = new ConsoleInputReader();
        System.setIn(stdin);

        assertThrows(
                NumberFormatException.class,
                reader::readPersonsExpenditure
        );
    }

}