package com.application.splitwise.input;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;
import com.application.splitwise.input.validations.InputValidator;
import com.application.splitwise.model.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class ConsoleInputReader implements InputReader {

    private final Scanner inputReader;
    private final InputValidator inputValidator;

    public ConsoleInputReader() {
        inputReader = new Scanner(System.in);
        inputValidator = new InputValidator();
    }

    @Override
    public List<Person> readPersonsExpenditure() {
        List<Person> personList = new ArrayList<>();
        String userInput;
        System.out.println("Please provide persons and expenditure below, enter 'exit' to stop");
        while (!((userInput = inputReader.nextLine()).equalsIgnoreCase("exit")) && isValidInput(userInput)) {
            Person person = parsePersonExpenditureInput(userInput);
            if(!isPersonExpenditureAlreadyRecorded(personList, person))
                personList.add(person);
        }

        return personList;
    }

    private boolean isPersonExpenditureAlreadyRecorded(List<Person> personList, Person person) {
        if(personList.contains(person)) {
            int personIndex = personList.indexOf(person);
            personList.get(personIndex).addExpenditure(person.getExpenditure());
            return true;
        }

        return false;
    }

    private boolean isValidInput(String userInput) {
        boolean isValidInput = false;
        try {
            isValidInput = inputValidator.isValidPersonExpenditureInput(userInput);
        } catch (InvalidInputFormatException | InvalidInputValueException exception) {
            System.err.print("Invalid Input Received from User");
        }

        return isValidInput;
    }

    private Person parsePersonExpenditureInput(String input) {

        String[] personData = input.split(",");
        return new Person(personData[0], Double.parseDouble(personData[1]));
    }
}
