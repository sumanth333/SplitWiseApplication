package com.application.splitwise.service;

import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Person;
import com.application.splitwise.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionsGenerator {

    public List<Transaction> generateTransactions(List<Expenditure> expenditures) {

        List<Transaction> transactions = new ArrayList<>();

        int index = 0;
        BigDecimal totalAmount = getTotalAmount(expenditures);
        while (index<expenditures.size()) {
            BigDecimal amountOfShare = getPersonShare(expenditures.get(index).getPerson()).multiply(totalAmount);
            BigDecimal amountSpent = expenditures.get(index).getAmount();
            int debtCompareToShare = amountSpent.compareTo(amountOfShare);
            if(debtCompareToShare < 0) {
                Expenditure debtorExpenditure = expenditures.get(index);
                transactions.addAll(getTransactionsForGivenDebt(debtorExpenditure, expenditures, totalAmount));
            }
            index++;
        }
        return transactions;
    }

    private List<Transaction> getTransactionsForGivenDebt(Expenditure debtorExpenditure , List<Expenditure> expenditures, BigDecimal totalAmount) {
        List<Transaction> transactions = new ArrayList<>();

        expenditures.stream()
                .filter(expenditure -> expenditure.getAmount().compareTo(totalAmount.multiply(getPersonShare(expenditure.getPerson()))) > 0
                        && expenditure.getStatus() != ExpenditureStatus.SETTLED
                        && debtorExpenditure.getStatus() != ExpenditureStatus.SETTLED)
                .forEach(expenditure -> {
                    addTransaction(debtorExpenditure, totalAmount, transactions, expenditure);
                });

        return transactions;
    }

    private void addTransaction(Expenditure debtorExpenditure, BigDecimal totalAmount,
                                List<Transaction> transactions, Expenditure expenditure) {
        BigDecimal debtorShare = (totalAmount.multiply(getPersonShare(debtorExpenditure.getPerson())));
        BigDecimal debtAmount = (debtorShare).subtract(debtorExpenditure.getAmount());
        BigDecimal beneficiaryShare = (totalAmount.multiply(getPersonShare(expenditure.getPerson())));
        BigDecimal amountToBeSettled = expenditure.getAmount().subtract(beneficiaryShare);

        if(!debtAmount.equals(BigDecimal.valueOf(0.0))) {
            if(amountToBeSettled.compareTo(debtAmount) >= 0.0) {
                updateExpenditure(debtorExpenditure, debtorShare);
                expenditure.updateExpenditure(expenditure.getAmount().subtract(debtAmount));
            } else {
                updateExpenditure(expenditure, beneficiaryShare);
                debtorExpenditure.updateExpenditure(debtorExpenditure.getAmount().add(amountToBeSettled));
            }

            BigDecimal amountPaid = amountToBeSettled.compareTo(debtAmount) >= 0.0 ? debtAmount: amountToBeSettled;
            transactions.add(new Transaction(debtorExpenditure.getPerson(), expenditure.getPerson(), amountPaid));
        }
    }

    private void updateExpenditure(Expenditure debtorExpenditure, BigDecimal equalShare) {
        debtorExpenditure.updateStatus(ExpenditureStatus.SETTLED);
        debtorExpenditure.updateExpenditure(equalShare);
    }


    private BigDecimal getPersonShare(Person person) {
        return BigDecimal.valueOf(PersonsShareProvider.getInstance().getPersonShare(person));
    }

    private BigDecimal getTotalAmount(List<Expenditure> expenditures) {
        BigDecimal totalAmount = BigDecimal.valueOf(0.0);

        for(Expenditure expenditure: expenditures) {
            totalAmount = totalAmount.add(expenditure.getAmount());
        }
        return totalAmount;
    }
}
