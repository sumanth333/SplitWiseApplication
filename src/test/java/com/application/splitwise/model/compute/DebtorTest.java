package com.application.splitwise.model.compute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DebtorTest {

    @Test
    void validateAttributesOfDebtor() {
        String name = "debtor";
        double amountInDebt = 100;

        Debtor debtor = new Debtor(name, amountInDebt);

        assertEquals(name, debtor.getName());
        assertEquals(amountInDebt, debtor.getAmountInDebt());
    }

    @Test
    void shouldThrowExceptionOnPartialValuesProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Debtor(null, 100.0));
        assertThrows(
                NullPointerException.class,
                () -> new Debtor("James Gosling", null));
    }

    @Test
    void shouldThrowExceptionIfNoValuesProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Debtor(null, null));
    }
}