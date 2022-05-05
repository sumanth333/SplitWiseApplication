package com.application.splitwise.service;


import com.application.splitwise.model.Person;
import com.application.splitwise.model.SplitExpensesLog;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void ShouldBeAbleToCreateValidExpenseLogsForGivenValues() {
        Debtor debtor1 = new Debtor("peter", 50.0);
        Debtor debtor2 = new Debtor("james", 100.0);
        Beneficiary beneficiary1 = new Beneficiary("adam", 70.0);
        Beneficiary beneficiary2 = new Beneficiary("john", 80.0);

        List<Debtor> debtors = new ArrayList<>(); debtors.add(debtor1); debtors.add(debtor2);
        List<Beneficiary> beneficiaries = new ArrayList<>(); beneficiaries.add(beneficiary1); beneficiaries.add(beneficiary2);

        SplitWiseOperations operations = new OperationsManager();
        List<SplitExpensesLog> splitExpensesLogs = operations.settleAmountBetweenDebtorsBeneficiaries(
                                                                debtors, beneficiaries);

        assertEquals(3, splitExpensesLogs.size());
    }

    @Test
    void shouldThrowAnExceptionForGivenInvalidValues() {
        Debtor debtor1 = new Debtor("peter", 50.0);
        Debtor debtor2 = new Debtor("james", 100.0);

        List<Debtor> debtors = new ArrayList<>(); debtors.add(debtor1); debtors.add(debtor2);
        List<Beneficiary> beneficiaries = new ArrayList<>();

        SplitWiseOperations operations = new OperationsManager();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> operations.settleAmountBetweenDebtorsBeneficiaries(
                        debtors, beneficiaries)
        );

    }
}