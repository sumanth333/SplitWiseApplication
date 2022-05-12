package com.application.splitwise.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void shouldThrowExceptionOnMandatoryValuesNotProvided() {
        assertThrows(
                NullPointerException.class,
                () -> new Person(null));
    }

    @Test
    void shouldReturnFalseOnComparingPersonWithInvalidObjects() {
        Person person = new Person("James");
        Object dummyObject = new Object();
        Person differentPerson = new Person("Ricky");

        assertNotEquals(person, dummyObject);
        assertNotEquals(person, differentPerson);
        assertNotEquals(person, null);
    }

    @Test
    void shouldReturnTrueOnComparingWithSamePersonName() {
        Person person1 = new Person("James");
        Person person2 = new Person("James");

        assertEquals(person1, person2);
    }

    @Test
    void shouldReturnAValidHashCodeForGivenPerson() {
        Person person = new Person("James");
        int expectedHashcode = 71338435;

        assertEquals(expectedHashcode, person.hashCode());
    }
}