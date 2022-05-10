package com.application.splitwise.service;

import com.application.splitwise.exceptions.EmptyExpenditureDataException;
import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TransactionsGenerator {

    public List<Transaction> generateTransactions(List<Expenditure> expenditures) {

        List<Transaction> transactions = new ArrayList<>();
        BigDecimal equalShare = null;

        try {
            equalShare = getEqualShare(expenditures);
        } catch (EmptyExpenditureDataException e) {
            System.err.println("Empty expenditure data provided for generateTransactions");
        }

        int index = 0;
        while (index<expenditures.size()) {
            BigDecimal amountSpent = expenditures.get(index).getAmount();
            int debtCompareToShare = amountSpent.compareTo(equalShare);
            if(debtCompareToShare < 0) {
                Expenditure debtorExpenditure = expenditures.get(index);
                transactions.addAll(getTransactionsForGivenDebt(debtorExpenditure, expenditures, equalShare));
            }
            index++;
        }
        return transactions;
    }

    private List<Transaction> getTransactionsForGivenDebt(Expenditure debtorExpenditure , List<Expenditure> expenditures, BigDecimal equalShare) {
        List<Transaction> transactions = new ArrayList<>();

        expenditures.stream()
                .filter(expenditure -> expenditure.getAmount().compareTo(equalShare) > 0
                        && expenditure.getStatus() != ExpenditureStatus.SETTLED
                        && debtorExpenditure.getStatus() != ExpenditureStatus.SETTLED)
                .forEach(expenditure -> {
                    BigDecimal debtAmount = equalShare.subtract(debtorExpenditure.getAmount());
                    BigDecimal amountToBeSettled = expenditure.getAmount().subtract(equalShare);

                    if(!debtAmount.equals(BigDecimal.valueOf(0.0))) {
                        if(amountToBeSettled.compareTo(debtAmount) >= 0.0) {
                            updateExpenditure(debtorExpenditure, equalShare);
                            expenditure.updateExpenditure(expenditure.getAmount().subtract(debtAmount));
                        } else {
                            updateExpenditure(expenditure, equalShare);
                            debtorExpenditure.updateExpenditure(debtorExpenditure.getAmount().add(amountToBeSettled));
                        }

                        BigDecimal amountPaid = amountToBeSettled.compareTo(debtAmount) >= 0.0 ? debtAmount: amountToBeSettled;
                        transactions.add(new Transaction(debtorExpenditure.getPerson(), expenditure.getPerson(), amountPaid));
                    }

                });

        return transactions;
    }

    private void updateExpenditure(Expenditure debtorExpenditure, BigDecimal equalShare) {
        debtorExpenditure.updateStatus(ExpenditureStatus.SETTLED);
        debtorExpenditure.updateExpenditure(equalShare);
    }


    private BigDecimal getEqualShare(List<Expenditure> expenditures) throws EmptyExpenditureDataException {

        if(expenditures.size() ==0 )
            throw new EmptyExpenditureDataException("No Expenditure Provided");

        BigDecimal sum = BigDecimal.valueOf(0.0);
        BigDecimal totalExpenditures = new BigDecimal(expenditures.size());
        for (Expenditure expenditure : expenditures) {
            sum = sum.add(expenditure.getAmount());
        }

        return sum.divide(totalExpenditures,2, RoundingMode.DOWN);
    }
}
