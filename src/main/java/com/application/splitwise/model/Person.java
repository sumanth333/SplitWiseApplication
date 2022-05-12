package com.application.splitwise.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Person {
    private final String id;
    private final String name;

    public Person(@NonNull String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
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