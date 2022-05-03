package com.application.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SplitExpensesLog {
    private String debtorName;
    private String beneficiaryName;
    private double amountTobePaid;
}
