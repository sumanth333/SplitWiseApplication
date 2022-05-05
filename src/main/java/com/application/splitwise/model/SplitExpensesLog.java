package com.application.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SplitExpensesLog {
    private String debtorName;
    private String beneficiaryName;
    private double amountTobePaid;
}
