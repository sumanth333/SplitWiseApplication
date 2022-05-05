package com.application.splitwise.service;


import com.application.splitwise.model.Person;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationsManagerTest {
    @Test
    void shouldReturnEmptyListWhenPersonsShareIsFair() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("testPerson1", 100.0);
        Person person2 = new Person("testPerson2", 100.0);
        personList.add(person1);personList.add(person2);

        OperationsManager operationsManager = new OperationsManager();
        List<Beneficiary> beneficiaries = operationsManager.findBeneficiaries(personList);
        List<Debtor> debtors = operationsManager.findDebtors(personList);

        assertTrue(beneficiaries.isEmpty());
        assertTrue(debtors.isEmpty());
    }

    @Test
    void shouldReturnValidBeneficiariesForGivenValues() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("testPerson1", 100.0);
        Person person2 = new Person("testPerson2", 50.0);
        personList.add(person1);personList.add(person2);

        OperationsManager operationsManager = new OperationsManager();
        List<Beneficiary> beneficiaries = operationsManager.findBeneficiaries(personList);

        assertEquals(1, beneficiaries.size());
        assertEquals(person1.getName(), beneficiaries.get(0).getName());

    }

    @Test
    void shouldReturnValidDebtorsForGivenValues() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("testPerson1", 100.0);
        Person person2 = new Person("testPerson2", 50.0);
        personList.add(person1);personList.add(person2);

        OperationsManager operationsManager = new OperationsManager();
        List<Debtor> debtors = operationsManager.findDebtors(personList);

        assertEquals(1, debtors.size());
        assertEquals(person2.getName(), debtors.get(0).getName());

    }

    @Test
    void shouldReturnEmptyListWhenNoDataProvided() {
        List<Person> personList = new ArrayList<>();

        OperationsManager operationsManager = new OperationsManager();
        List<Beneficiary> beneficiaries = operationsManager.findBeneficiaries(personList);
        List<Debtor> debtors = operationsManager.findDebtors(personList);

        assertTrue(beneficiaries.isEmpty());
        assertTrue(debtors.isEmpty());
    }
}