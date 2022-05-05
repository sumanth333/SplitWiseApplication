package com.application.splitwise.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SplitExpensesLogTest {

    @Test
    void validateGivenExpensesLogDetails() {
        String debtorName = "SampleDebtor";
        String beneficiaryName = "sampleBeneficiary";
        double amountToBePaid = 100;

        SplitExpensesLog splitExpensesLog = new SplitExpensesLog(debtorName, beneficiaryName, amountToBePaid);
        assertEquals(debtorName, splitExpensesLog.getDebtorName());
        assertEquals(beneficiaryName, splitExpensesLog.getBeneficiaryName());
        assertEquals(amountToBePaid, splitExpensesLog.getAmountTobePaid());
    }

    @Test
    void shouldThrowExceptionOnMandatoryValuesNotProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new SplitExpensesLog(null, null, 100.0));
        assertThrows(
                NullPointerException.class,
                () -> new SplitExpensesLog("Adam", null, null));
    }

    @Test
    void shouldThrowExceptionOnAllNullValuesProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new SplitExpensesLog(null, null, null));
    }
}