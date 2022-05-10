package com.application.splitwise.model;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Transaction {
    private final String id;
    private final Person payer;
    private final Person receiver;
    private final BigDecimal amount;

    public Transaction(@NonNull Person payer, @NonNull Person receiver, @NonNull BigDecimal amount) {
        id = UUID.randomUUID().toString();
        this.payer = payer;
        this.receiver = receiver;
        this.amount = amount;
    }
}
