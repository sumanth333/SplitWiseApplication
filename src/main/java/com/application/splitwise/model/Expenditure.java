package com.application.splitwise.model;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Expenditure {

    private final String id;
    private final Person person;
    private BigDecimal amount;
    private ExpenditureStatus status;

    public Expenditure(@NonNull Person person, @NonNull BigDecimal amount
            , @NonNull ExpenditureStatus status) {
        id = UUID.randomUUID().toString();
        this.person = person;
        this.amount = amount;
        this.status = status;
    }

    public void updateExpenditure(BigDecimal updatedExpenditure) {
        amount = updatedExpenditure;
    }

    public void updateStatus(ExpenditureStatus updatedStatus) {
        status = updatedStatus;
    }
}
