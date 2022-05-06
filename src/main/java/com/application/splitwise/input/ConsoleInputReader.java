package com.application.splitwise.input;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;
import com.application.splitwise.input.validations.InputValidator;
import com.application.splitwise.input.validations.Validator;
import com.application.splitwise.model.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class ConsoleInputReader implements InputReader {

    private final Scanner inputReader;
    private final Validator inputValidator;

    public ConsoleInputReader() {
        inputReader = new Scanner(System.in);
        inputValidator = new InputValidator();
    }

    @Override
    public List<Person> readPersonsExpenditure() {
        List<Person> personList = new ArrayList<>();
        String userInput;

        while (!((userInput = inputReader.nextLine()).isEmpty()) && isValidInput(userInput)) {
            personList.add(parsePersonExpenditureInput(userInput));
        }

        return personList;
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
