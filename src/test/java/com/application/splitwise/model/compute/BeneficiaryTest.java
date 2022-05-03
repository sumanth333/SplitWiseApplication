package com.application.splitwise.model.compute;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void validateUpdatingTheAttributesOfBeneficiary() {
        Beneficiary beneficiary = new Beneficiary("sampleBeneficiary", 100);

        String updatedName = "updatedBeneficiaryName";
        double updatedAmount = 500;
        beneficiary.setName(updatedName);
        beneficiary.setAmountTobeReceived(updatedAmount);

        assertEquals(updatedName, beneficiary.getName());
        assertEquals(updatedAmount, beneficiary.getAmountTobeReceived());

    }

}