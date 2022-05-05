package com.application.splitwise.input;

import com.application.splitwise.model.Person;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class ConsoleInputReader implements InputReader{

    private Scanner inputReader;

    public ConsoleInputReader() {
        inputReader = new Scanner(System.in);
    }

    @Override
    public List<Person> readPersonsExpenditure() {
        List<Person> personList = new ArrayList<>();
        String userInput;

        while(!((userInput= inputReader.nextLine()).isEmpty())) {
            personList.add(parsePersonExpenditureInput(userInput));
        }

        return personList;
    }

    private Person parsePersonExpenditureInput(String input) {
        String[] personData = input.split(",");
        return new Person(personData[0], Double.parseDouble(personData[1]));
    }
}
