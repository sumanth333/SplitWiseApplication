package com.application.splitwise.model;

public class Person {
    private String name;
    private Long expenditure;

    public Person(String name, Long expenditure) {
        this.name = name;
        this.expenditure = expenditure;
    }

    public String getName() {
        return name;
    }

    public Long getExpenditure() {
        return expenditure;
    }

}