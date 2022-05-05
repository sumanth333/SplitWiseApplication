package com.application.splitwise.service;

import com.application.splitwise.model.Person;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;

import java.util.ArrayList;
import java.util.List;

public class OperationsManager implements SplitWiseOperations{

    @Override
    public List<Debtor> findDebtors(List<Person> personsList) {
        List<Debtor> debtors = new ArrayList<>();
        Double equalShare = findEqualShare(personsList);

        for(Person person: personsList) {
            Double expenditure = person.getExpenditure();
            if(expenditure < equalShare)
                debtors.add(new Debtor(person.getName(), (equalShare-expenditure)));
        }

        return debtors;
    }

    @Override
    public List<Beneficiary> findBeneficiaries(List<Person> personsList) {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        Double equalShare = findEqualShare(personsList);

        for(Person person: personsList) {
            Double expenditure = person.getExpenditure();
            if(expenditure > equalShare)
                beneficiaries.add(new Beneficiary(person.getName(), (equalShare-expenditure)));
        }

        return beneficiaries;
    }

    private Double findEqualShare(List<Person> personsList) {
        double sum = 0.0;
        for(Person person: personsList) {
            sum += person.getExpenditure();
        }

        return sum/personsList.size();
    }
}
