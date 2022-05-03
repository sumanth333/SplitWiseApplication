package com.application.splitwise.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SplitExpensesLogTest {

    @Test
    void validateExpensesLogDetails() {
        String debtorName = "SampleDebtor";
        String beneficiaryName = "sampleBeneficiary";
        double amountToBePaid = 100;

        SplitExpensesLog splitExpensesLog = new SplitExpensesLog(debtorName, beneficiaryName, amountToBePaid);
        assertEquals(debtorName, splitExpensesLog.getDebtorName());
        assertEquals(beneficiaryName, splitExpensesLog.getBeneficiaryName());
        assertEquals(amountToBePaid, splitExpensesLog.getAmountTobePaid());
    }

}