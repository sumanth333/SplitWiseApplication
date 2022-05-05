package com.application.splitwise.model.compute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BeneficiaryTest {

    @Test
    void validateBeneficiaryWithGivenValues() {
        String name = "sampleBeneficiary";
        double amountToBeReceived = 100;
        Beneficiary beneficiary = new Beneficiary(name, amountToBeReceived);

        assertEquals(name, beneficiary.getName());
        assertEquals(amountToBeReceived, beneficiary.getAmountTobeReceived());
    }

    @Test
    void shouldThrowExceptionOnRequiredValuesNotProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Beneficiary(null, 100.0));
        assertThrows(
                NullPointerException.class,
                () -> new Beneficiary("james", null));
    }

    @Test
    void shouldThrowExceptionIfNoValuesProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Beneficiary(null, null));
    }
}