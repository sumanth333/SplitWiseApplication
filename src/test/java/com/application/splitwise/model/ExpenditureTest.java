package com.application.splitwise.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ExpenditureTest {

    @Test
    void shouldThrowExceptionOnInstantiatingWithInvalidValues() {
        Person person = new Person("James");
        BigDecimal amount = new BigDecimal(123);
        assertThrows(
                NullPointerException.class,
                () -> new Expenditure(null, null,null)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Expenditure(null, amount, ExpenditureStatus.UNSETTLED)
        );
        assertThrows(
                NullPointerException.class,
                () -> new Expenditure(person, amount, null)
        );
    }
}