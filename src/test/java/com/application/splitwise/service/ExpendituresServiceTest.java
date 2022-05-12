package com.application.splitwise.service;

import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Person;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpendituresServiceTest {

    @Test
    void shouldBeAbleToCreateNewExpenditureWithGivenValues() {
        Person person = new Person("testPerson");
        BigDecimal amount = BigDecimal.valueOf(100.0);
        Expenditure expenditure = ExpendituresService.getInstance().createNewExpenditure(person, amount);

        assertEquals(person, expenditure.getPerson());
        assertEquals(amount, expenditure.getAmount());
        assertEquals(ExpenditureStatus.UNSETTLED, expenditure.getStatus());
    }

    @Test
    void shouldReturnZeroForGettingTotalAmountOnEmptyExpenditures() {
        List<Expenditure> expenditures = new ArrayList<>();
        BigDecimal totalAmount = ExpendituresService.getInstance().getTotalAmountOfExpenditures(expenditures);
        assertEquals(BigDecimal.valueOf(0.0), totalAmount);
    }

    @Test
    void shouldReturnValidTotalAmountForGivenExpenditures() {
        ExpendituresService expendituresService = ExpendituresService.getInstance();

        List<Expenditure> expenditures = new ArrayList<>();
        expenditures.add(expendituresService.createNewExpenditure(new Person("testPerson1"),BigDecimal.valueOf(50.0)));
        expenditures.add(expendituresService.createNewExpenditure(new Person("testPerson2"),BigDecimal.valueOf(100.0)));

        BigDecimal totalAmount = expendituresService.getTotalAmountOfExpenditures(expenditures);
        assertEquals(BigDecimal.valueOf(150.0), totalAmount);
    }

    @Test
    void shouldUpdateExpenditureAmountOfSamePerson() {
        ExpendituresService expendituresService = ExpendituresService.getInstance();

        Person testPerson = new Person("testPerson");
        List<Expenditure> expenditures = new ArrayList<>();
        expenditures.add(expendituresService.createNewExpenditure(testPerson,BigDecimal.valueOf(50.0)));
        expendituresService.updateExpenditureOfGivenPerson(expenditures, testPerson, BigDecimal.valueOf(120));

        assertEquals(BigDecimal.valueOf(170.0), expenditures.get(0).getAmount());
    }
    @Test
    void shouldUpdateTheStatusOfGivenExpenditure() {
        ExpendituresService expendituresService = ExpendituresService.getInstance();

        Expenditure expenditure = expendituresService.createNewExpenditure(new Person("testPerson"), BigDecimal.valueOf(10.0));
        assertEquals(ExpenditureStatus.UNSETTLED, expenditure.getStatus());
        expendituresService.updateExpenditureStatus(expenditure);

        assertEquals(ExpenditureStatus.SETTLED, expenditure.getStatus());
    }

}