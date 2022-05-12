package com.application.splitwise.service;

import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionsGenerator {
    private final PersonsShareService personsShareService;
    private final ExpendituresService expendituresService;

    public TransactionsGenerator() {
        personsShareService = PersonsShareService.getInstance();
        expendituresService = ExpendituresService.getInstance();
    }

    public List<Transaction> generateTransactions(List<Expenditure> expenditures) {
        List<Transaction> transactions = new ArrayList<>();

        BigDecimal totalAmount = ExpendituresService.getInstance().getTotalAmountOfExpenditures(expenditures);
        for (Expenditure debtorExpenditure : expenditures) {
            BigDecimal amountOfShare = personsShareService.getPersonShare(debtorExpenditure.getPerson()).multiply(totalAmount);
            BigDecimal amountSpent = debtorExpenditure.getAmount();
            int debtCompareToShare = amountSpent.compareTo(amountOfShare);
            if (debtCompareToShare < 0) {
                transactions.addAll(getTransactionsForGivenDebt(debtorExpenditure, expenditures, totalAmount));
            }
        }
        return transactions;
    }

    private List<Transaction> getTransactionsForGivenDebt(Expenditure debtorExpenditure, List<Expenditure> expenditures, BigDecimal totalAmount) {
        List<Transaction> transactions = new ArrayList<>();

        expenditures.stream()
                .filter(expenditure -> expenditure.getAmount().compareTo(totalAmount.multiply(
                        personsShareService.getPersonShare(expenditure.getPerson()))) > 0
                        && expenditure.getStatus() != ExpenditureStatus.SETTLED
                        && debtorExpenditure.getStatus() != ExpenditureStatus.SETTLED)
                .forEach(expenditure -> {
                    addNewTransaction(debtorExpenditure, totalAmount, transactions, expenditure);
                });

        return transactions;
    }

    private void addNewTransaction(Expenditure debtorExpenditure, BigDecimal totalAmount,
                                   List<Transaction> transactions, Expenditure expenditure) {
        BigDecimal debtorShare = (totalAmount.multiply(personsShareService.getPersonShare(debtorExpenditure.getPerson())));
        BigDecimal debtAmount = (debtorShare).subtract(debtorExpenditure.getAmount());
        BigDecimal beneficiaryShare = (totalAmount.multiply(personsShareService.getPersonShare(expenditure.getPerson())));
        BigDecimal amountToBeSettled = expenditure.getAmount().subtract(beneficiaryShare);

        if (!debtAmount.equals(BigDecimal.valueOf(0.0))) {
            if (amountToBeSettled.compareTo(debtAmount) >= 0.0) {
                expendituresService.updateExpenditureStatus(debtorExpenditure);
                expenditure.deleteAmount(debtAmount);
                debtorExpenditure.addAmount(debtAmount);
            } else {
                expendituresService.updateExpenditureStatus(expenditure);
                debtorExpenditure.addAmount(amountToBeSettled);
                expenditure.deleteAmount(amountToBeSettled);
            }
            BigDecimal amountPaid = amountToBeSettled.compareTo(debtAmount) >= 0.0 ? debtAmount : amountToBeSettled;
            transactions.add(new Transaction(debtorExpenditure.getPerson(), expenditure.getPerson(), amountPaid));
        }
    }
}
