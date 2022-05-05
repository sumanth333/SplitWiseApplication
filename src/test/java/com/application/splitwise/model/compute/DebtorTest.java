package com.application.splitwise.model.compute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DebtorTest {

    @Test
    void validateAttributesOfDebtor() {
        String name = "debtor";
        double amountInDebt = 100;

        Debtor debtor = new Debtor(name, amountInDebt);

        assertEquals(name, debtor.getName());
        assertEquals(amountInDebt, debtor.getAmountInDebt());
    }
}