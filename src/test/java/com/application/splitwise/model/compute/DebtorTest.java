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

    @Test
    void validateUpdatedAttributesOfGivenDebtor() {
        Debtor debtor = new Debtor("temporaryName", 100);

        String updatedName = "updatedDebtor";
        double updatedAmountInDebt = 300;
        debtor.setName(updatedName);
        debtor.setAmountInDebt(updatedAmountInDebt);

        assertEquals(updatedName, debtor.getName());
        assertEquals(updatedAmountInDebt, debtor.getAmountInDebt());
    }
}