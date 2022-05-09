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

    public void addExpenditure(double amount) {
        this.expenditure += amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj.getClass() != this.getClass())
            return false;

        final Person other = (Person) obj;
        if (!this.name.equals(other.name)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.name.hashCode();
        return hash;
    }

}