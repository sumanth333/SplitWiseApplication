package com.application.splitwise.input;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;
import com.application.splitwise.input.validations.InputExpenditureValidator;
import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.ExpenditureStatus;
import com.application.splitwise.model.Person;
import com.application.splitwise.service.PersonsShareProvider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleExpenditureReader implements ExpenditureReader {

    private final Scanner inputReader;
    private final InputExpenditureValidator inputExpenditureValidator;

    public ConsoleExpenditureReader() {
        inputReader = new Scanner(System.in);
        inputExpenditureValidator = new InputExpenditureValidator();
    }

    @Override
    public List<Expenditure> readExpenditures() {
        List<Person> personList = new ArrayList<>();
        List<Expenditure> expenditures = new ArrayList<>();

        String userInput;
        System.out.println("Please provide persons and expenditure below, enter 'exit' to stop");
        while (!((userInput = inputReader.nextLine()).equalsIgnoreCase("exit")) && isValidInput(userInput)) {
            Person person = getPersonFromInput(userInput);
            createPersonShareFromInput(person, userInput);
            BigDecimal amount = getExpenditureFromInput(userInput);
            if (personList.contains(person)) {
                updateExpenditureOfGivenPerson(expenditures, person, amount);
            } else {
                personList.add(person);
                createNewExpenditure(expenditures, person, amount );
            }
        }

        return expenditures;
    }

    private void createPersonShareFromInput(Person person, String input) {
        String[] inputDta = input.split(",");
        PersonsShareProvider personsShareProvider = PersonsShareProvider.getInstance();
        Double share = null;
        if(inputDta.length == 3)
            share = Double.parseDouble(inputDta[2]);

        personsShareProvider.addNewPersonShare(person, share);
    }

    private void createNewExpenditure(List<Expenditure> expenditures, Person person, BigDecimal amount) {
        expenditures.add(new Expenditure(person, amount, ExpenditureStatus.UNSETTLED));
    }

    private void updateExpenditureOfGivenPerson(List<Expenditure> expenditures, Person person, BigDecimal amount) {
        expenditures.stream()
                .filter(expenditure -> expenditure.getPerson().equals(person))
                .forEach(expenditure -> expenditure.updateExpenditure(expenditure.getAmount().add(amount)));
    }

    private boolean isValidInput(String userInput) {
        boolean isValidInput = false;
        try {
            isValidInput = inputExpenditureValidator.isValidPersonExpenditureInput(userInput);
        } catch (InvalidInputFormatException | InvalidInputValueException exception) {
            System.err.print("Invalid Input Received from User");
        }

        return isValidInput;
    }

    private Person getPersonFromInput(String input) {
        String[] personData = input.split(",");

        return new Person(personData[0]);
    }

    private BigDecimal getExpenditureFromInput(String input) {
        String[] inputDta = input.split(",");

        return new BigDecimal(inputDta[1]);
    }
}
