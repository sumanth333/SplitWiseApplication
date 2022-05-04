package com.application.splitwise.input;

import com.application.splitwise.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInputReader implements InputReader{

    Scanner input;

    public ConsoleInputReader() {
        input = new Scanner(System.in);
    }

    @Override
    public List<Person> readPersonsExpenditure() {
        List<Person> personList = new ArrayList<>();
        String userInput;

        while(!(userInput=input.nextLine()).isEmpty()) {
            String[] userData = userInput.split(",");
            personList.add(new Person(userData[0], Long.parseLong(userData[1])));
        }

        return personList;
    }

}
