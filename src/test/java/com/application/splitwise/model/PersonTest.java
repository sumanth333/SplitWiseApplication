package com.application.splitwise.model;


import com.application.splitwise.model.compute.Beneficiary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldThrowExceptionOnMandatoryValuesNotProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Person(null, 100.0));
        assertThrows(
                NullPointerException.class,
                () -> new Person("Adam", null));
    }
    @Test
    void shouldReturnFalseOnComparingPersonWithInvalidObjects() {
        Person person = new Person("James", 100.0);
        Beneficiary beneficiary = new Beneficiary("sample", 50.0);

        assertFalse(person.equals(beneficiary));
        assertFalse(person.equals(null));
    }

    @Test
    void shouldReturnAValidHashCodeForGivenPerson() {
        Person person = new Person("James", 100.0);
        int expectedHashcode = 71338435;

        assertEquals(expectedHashcode, person.hashCode());
    }

    @Test
    void shouldThrowExceptionOnAllNullValuesProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Person(null, null));
    }
}