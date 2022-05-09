package com.application.splitwise.app;

import com.application.splitwise.input.ConsoleInputReader;
import com.application.splitwise.input.InputReader;
import com.application.splitwise.model.Person;
import com.application.splitwise.model.SplitExpensesLog;
import com.application.splitwise.model.compute.Beneficiary;
import com.application.splitwise.model.compute.Debtor;
import com.application.splitwise.output.ConsoleOutputWriter;
import com.application.splitwise.output.OutputWriter;
import com.application.splitwise.service.SplitWiseOperationsManager;
import lombok.Setter;

import java.util.List;

@Setter
public class SplitWiseApp {
    private InputReader reader;
    private SplitWiseOperationsManager splitWiseOperationsManager;
    private OutputWriter outputWriter;

    public SplitWiseApp() {
        reader = new ConsoleInputReader();
        outputWriter = new ConsoleOutputWriter();
        splitWiseOperationsManager = new SplitWiseOperationsManager();
    }

    public void run() {
        List<Person> personsList = reader.readPersonsExpenditure();
        List<SplitExpensesLog> listOfSplitExpensesLog = processPersonsExpenditure(personsList);
        outputWriter.writeSplitExpensesLog(listOfSplitExpensesLog);
    }

    private List<SplitExpensesLog> processPersonsExpenditure(List<Person> personsList) {
        List<Debtor> debtors = splitWiseOperationsManager.findDebtors(personsList);
        List<Beneficiary> beneficiaries = splitWiseOperationsManager.findBeneficiaries(personsList);

        return splitWiseOperationsManager.settleAmountBetweenDebtorsBeneficiaries(debtors, beneficiaries);
    }
}
