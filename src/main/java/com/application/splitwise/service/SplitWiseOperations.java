package com.application.splitwise.service;

import com.application.splitwise.model.Person;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;

import java.util.List;

public interface SplitWiseOperations {
    List<Debtor> findDebtors(List<Person> personsList);
    List<Beneficiary> findBeneficiaries(List<Person> personsList);
}
