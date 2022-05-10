package com.application.splitwise.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void shouldThrowExceptionOnNotProvidingMandatoryValues() {
        Person dummyPerson = new Person("dummy");
        BigDecimal dummyDecimal = new BigDecimal("10");
        assertThrows(
                NullPointerException.class,
                () -> new Transaction(dummyPerson, dummyPerson, null)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Transaction(null, dummyPerson, dummyDecimal)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Transaction(null, null, null)
        );
    }
}