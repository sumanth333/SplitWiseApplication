package com.application.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class Person {
    @NonNull
    private String name;
    @NonNull
    private Double expenditure;
}