package com.application.splitwise.model.compute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Debtor {
    @NonNull
    private String name;
    @NonNull
    private Double amountInDebt;

    public void setAmountInDebt(Double amountInDebt) {
        this.amountInDebt = amountInDebt;
    }
}
