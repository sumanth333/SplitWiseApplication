package com.application.splitwise.service;

import com.application.splitwise.model.Person;
import com.application.splitwise.model.SplitExpensesLog;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;

import java.util.ArrayList;
import java.util.List;

public class OperationsManager implements SplitWiseOperations {

    @Override
    public List<Debtor> findDebtors(List<Person> personsList) {
        List<Debtor> debtors = new ArrayList<>();
        Double equalShare = findEqualShare(personsList);

        for (Person person : personsList) {
            Double expenditure = person.getExpenditure();
            if (expenditure < equalShare)
                debtors.add(new Debtor(person.getName(), (equalShare - expenditure)));
        }

        return debtors;
    }

    @Override
    public List<Beneficiary> findBeneficiaries(List<Person> personsList) {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        Double equalShare = findEqualShare(personsList);

        for (Person person : personsList) {
            Double expenditure = person.getExpenditure();
            if (expenditure > equalShare)
                beneficiaries.add(new Beneficiary(person.getName(), (expenditure - equalShare)));
        }

        return beneficiaries;
    }

    @Override
    public List<SplitExpensesLog> settleAmountBetweenDebtorsBeneficiaries(
            List<Debtor> debtors, List<Beneficiary> beneficiaries) {
        List<SplitExpensesLog> listOfSplitExpensesLogs = new ArrayList<>();

        int debtorIndex = 0;
        int beneficiaryIndex = 0;

        while (debtorIndex < debtors.size()) {
            double debtAmount = debtors.get(debtorIndex).getAmountInDebt();
            double benefitAmount = beneficiaries.get(beneficiaryIndex).getAmountToBeReceived();
            double amountAssigned = (benefitAmount == debtAmount) ? benefitAmount : Math.abs(benefitAmount - debtAmount);

            //Add SplitExpenseLog
            listOfSplitExpensesLogs.add(new SplitExpensesLog(debtors.get(debtorIndex).getName(),
                    beneficiaries.get(beneficiaryIndex).getName(), amountAssigned));

            //update the indexes
            if (benefitAmount > debtAmount) {
                beneficiaries.get(beneficiaryIndex).setAmountToBeReceived(benefitAmount - debtAmount);
                debtorIndex++;
            } else if (benefitAmount < debtAmount) {
                debtors.get(debtorIndex).setAmountInDebt(debtAmount - benefitAmount);
                beneficiaryIndex++;
            } else {
                debtorIndex++;
                beneficiaryIndex++;
            }
        }

        return listOfSplitExpensesLogs;
    }

    private Double findEqualShare(List<Person> personsList) {
        double sum = 0.0;
        for (Person person : personsList) {
            sum += person.getExpenditure();
        }

        return sum / personsList.size();
    }
}
