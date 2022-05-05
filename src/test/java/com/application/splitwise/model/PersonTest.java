package com.application.splitwise.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonTest {

    @Test
    void shouldCreateObjectForPerson() {
        String expectedName = "person";
        double expectedExpenditure = 10;

        Person person = new Person(expectedName, expectedExpenditure);

        assertNotNull(person);
    }

    @Test
    void validateGivenPersonAttributes() {
        String name = "Peter";
        double expenditure = 100;

        Person person = new Person(name, expenditure);

        assertEquals(name, person.getName());
        assertEquals(expenditure, person.getExpenditure());
    }
}