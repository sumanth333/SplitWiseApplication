package com.application.splitwise.app;

import com.application.splitwise.input.ConsoleInputReader;
import com.application.splitwise.input.InputReader;
import com.application.splitwise.model.Person;
import com.application.splitwise.model.SplitExpensesLog;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;
import com.application.splitwise.output.ConsoleOutputWriter;
import com.application.splitwise.output.OutputWriter;
import com.application.splitwise.service.OperationsManager;
import com.application.splitwise.service.SplitWiseOperations;

import java.util.List;

public class SplitWiseApp implements App {
    InputReader reader;
    SplitWiseOperations operationsManager;
    OutputWriter outputWriter;

    public SplitWiseApp() {
        reader = new ConsoleInputReader();
        outputWriter = new ConsoleOutputWriter();
        operationsManager = new OperationsManager();
    }

    @Override
    public void run() {
        List<Person> personsList = reader.readPersonsExpenditure();
        List<SplitExpensesLog> listOfSplitExpensesLog = processPersonsExpenditure(personsList);
        outputWriter.writeSplitExpensesLog(listOfSplitExpensesLog);
    }

    private List<SplitExpensesLog> processPersonsExpenditure(List<Person> personsList) {
        List<Debtor> debtors = operationsManager.findDebtors(personsList);
        List<Beneficiary> beneficiaries = operationsManager.findBeneficiaries(personsList);

        return operationsManager.settleAmountBetweenDebtorsBeneficiaries(debtors, beneficiaries);
    }
}
