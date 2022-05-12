package com.application.splitwise.input;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;
import com.application.splitwise.input.validations.InputExpenditureValidator;
import com.application.splitwise.model.Expenditure;
import com.application.splitwise.model.Person;
import com.application.splitwise.service.ExpendituresService;
import com.application.splitwise.service.PersonsShareService;

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
        ExpendituresService expendituresService = ExpendituresService.getInstance();
        while (!((userInput = inputReader.nextLine()).equalsIgnoreCase("exit")) && isValidInput(userInput)) {
            Person person = getPersonFromInput(userInput);
            PersonsShareService.getInstance().createPersonShare(person, userInput);
            BigDecimal amount = getExpenditureAmountFromInput(userInput);
            if (!personList.contains(person)) {
                personList.add(person);
                expenditures.add(expendituresService.createNewExpenditure(person, amount ));
            } else {
                expendituresService.updateExpenditureOfGivenPerson(expenditures, person, amount);
            }
        }

        return expenditures;
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

    private BigDecimal getExpenditureAmountFromInput(String input) {
        String[] inputDta = input.split(",");

        return new BigDecimal(inputDta[1]);
    }
}
