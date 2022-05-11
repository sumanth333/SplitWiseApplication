package com.application.splitwise.service;

import com.application.splitwise.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonsShareProviderTest {

    @BeforeEach
    void setUp() {
        PersonsShareProvider.getInstance().clearExistingShareDetails();
    }

    @Test
    void shouldGetEqualSharePerPersonWhenNoShareDetailsProvided() {
        Person testPerson1 = new Person("testPerson1");
        Person testPerson2 = new Person("testPerson2");

        PersonsShareProvider personsShareProvider = PersonsShareProvider.getInstance();
        personsShareProvider.addNewPersonShare(testPerson1, null);
        personsShareProvider.addNewPersonShare(testPerson2, null);

        assertEquals(0.5, personsShareProvider.getPersonShare(testPerson1));
        assertEquals(0.5, personsShareProvider.getPersonShare(testPerson2));
    }
    @Test
    void ShouldGetExpectedShareOfEachPersonAdded() {
        Person testPerson1 = new Person("testPerson1");
        Person testPerson2 = new Person("testPerson2");
        Person testPerson3 = new Person("testPerson3");

        PersonsShareProvider personsShareProvider = PersonsShareProvider.getInstance();
        personsShareProvider.addNewPersonShare(testPerson1, 0.25);
        personsShareProvider.addNewPersonShare(testPerson2, 0.40);
        personsShareProvider.addNewPersonShare(testPerson3, 0.35);

        assertEquals(0.25, personsShareProvider.getPersonShare(testPerson1));
        assertEquals(0.40, personsShareProvider.getPersonShare(testPerson2));
        assertEquals(0.35, personsShareProvider.getPersonShare(testPerson3));
    }

}