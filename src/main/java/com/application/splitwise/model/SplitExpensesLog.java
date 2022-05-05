package com.application.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class SplitExpensesLog {
    @NonNull
    private String debtorName;
    @NonNull
    private String beneficiaryName;
    @NonNull
    private Double amountTobePaid;
}
