package com.application.splitwise.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void shouldBeAbleToModifySplitExpensesLogDetails() {
        SplitExpensesLog splitExpensesLog = new SplitExpensesLog("debtorName", "beneficiaryName", 10);

        String updatedDebtorName = "SampleDebtor";
        String updatedBeneficiaryName = "sampleBeneficiary";
        double updatedAmountToBePaid = 100;
        splitExpensesLog.setDebtorName(updatedDebtorName);
        splitExpensesLog.setBeneficiaryName(updatedBeneficiaryName);
        splitExpensesLog.setAmountTobePaid(updatedAmountToBePaid);

        assertEquals(updatedDebtorName, splitExpensesLog.getDebtorName());
        assertEquals(updatedBeneficiaryName, splitExpensesLog.getBeneficiaryName());
        assertEquals(updatedAmountToBePaid, splitExpensesLog.getAmountTobePaid());

    }

}