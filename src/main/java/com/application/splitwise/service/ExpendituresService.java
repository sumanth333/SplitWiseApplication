package com.application.splitwise.service;

import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Person;

import java.math.BigDecimal;
import java.util.List;

public class ExpendituresService {
    private static ExpendituresService expendituresService = null;

    private ExpendituresService() {
    }

    public static ExpendituresService getInstance() {
        if (expendituresService == null) {
            expendituresService = new ExpendituresService();
        }
        return expendituresService;
    }

    public Expenditure createNewExpenditure(Person person, BigDecimal amount) {
        return new Expenditure(person, amount, ExpenditureStatus.UNSETTLED);
    }

    public void updateExpenditureOfGivenPerson(List<Expenditure> expenditures, Person person, BigDecimal amount) {
        expenditures.stream()
                .filter(expenditure -> expenditure.getPerson().equals(person))
                .forEach(expenditure -> expenditure.addAmount(amount));
    }

    public void updateExpenditureStatus(Expenditure expenditure) {
        expenditure.settleAmount();
    }

    public BigDecimal getTotalAmountOfExpenditures(List<Expenditure> expenditures) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);

        for (Expenditure expenditure : expenditures) {
            totalAmount = totalAmount.add(expenditure.getAmount());
        }
        return totalAmount;
    }

    public void updateTransactionAmountInExpenditure(Expenditure debtorExpenditure, Expenditure expenditure, BigDecimal debtAmount) {
        expenditure.deleteAmount(debtAmount);
        debtorExpenditure.addAmount(debtAmount);
    }
}