package com.application.splitwise.model.compute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Beneficiary {
    @NonNull
    private String name;
    @NonNull
    private Double amountToBeReceived;

    public void setAmountToBeReceived(Double amountToBeReceived) {
        this.amountToBeReceived = amountToBeReceived;
    }
}
