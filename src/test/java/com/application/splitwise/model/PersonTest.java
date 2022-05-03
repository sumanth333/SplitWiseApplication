package com.application.splitwise.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonTest {

    @Test
    void shouldCreateObjectForPerson() {
        String expectedName = "person";
        long expectedExpenditure = 10;

        Person person = new Person(expectedName, expectedExpenditure);

        assertNotNull(person);
    }

    @Test
    void validatePersonStateValues() {
        String name = "Peter";
        long expenditure = 100L;

        Person person = new Person(name, expenditure);

        assertEquals(name, person.getName());
        assertEquals(expenditure, person.getExpenditure());

    }

}