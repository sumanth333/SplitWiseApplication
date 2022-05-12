package com.application.splitwise.service;

import com.application.splitwise.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonsShareServiceTest {

    @BeforeEach
    void setUp() {
        PersonsShareService.getInstance().clearExistingShareDetails();
    }

    @Test
    void shouldGetEqualSharePerPersonWhenNoShareDetailsProvided() {
        Person testPerson1 = new Person("testPerson1");
        Person testPerson2 = new Person("testPerson2");

        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.addNewPersonShare(testPerson1, null);
        personsShareService.addNewPersonShare(testPerson2, null);

        assertEquals(BigDecimal.valueOf(0.5), personsShareService.getPersonShare(testPerson1));
        assertEquals(BigDecimal.valueOf(0.5), personsShareService.getPersonShare(testPerson2));
    }
    @Test
    void ShouldGetExpectedShareOfEachPersonAdded() {
        Person testPerson1 = new Person("testPerson1");
        Person testPerson2 = new Person("testPerson2");
        Person testPerson3 = new Person("testPerson3");

        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.addNewPersonShare(testPerson1, 0.25);
        personsShareService.addNewPersonShare(testPerson2, 0.40);
        personsShareService.addNewPersonShare(testPerson3, 0.35);

        assertEquals(BigDecimal.valueOf(0.25), personsShareService.getPersonShare(testPerson1));
        assertEquals(BigDecimal.valueOf(0.40), personsShareService.getPersonShare(testPerson2));
        assertEquals(BigDecimal.valueOf(0.35), personsShareService.getPersonShare(testPerson3));
    }

    @Test
    void shouldCreateANewPersonShareFromInputAndAddToShareList() {
        Person testPerson1 = new Person("testPerson1");

        PersonsShareService personsShareService = PersonsShareService.getInstance();
        personsShareService.createPersonShare(testPerson1, "testPerson1,100,0.5");

        assertEquals(BigDecimal.valueOf(0.5), personsShareService.getPersonShare(testPerson1));
    }

}