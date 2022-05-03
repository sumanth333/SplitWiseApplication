package com.application.splitwise.model.compute;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Beneficiary {
    private String name;
    private double amountTobeReceived;
}
