package com.application.splitwise.service;

import com.application.splitwise.model.Person;

import java.util.HashMap;

public class PersonsShareProvider {

    private static PersonsShareProvider personsShareProvider = null;
    private static HashMap<Person, Double> personsShare;

    private PersonsShareProvider(){}

    public static PersonsShareProvider getInstance() {
        if(personsShareProvider == null) {
            personsShareProvider = new PersonsShareProvider();
            personsShare = new HashMap<>();
        }
        return personsShareProvider;
    }

    public Double getPersonShare(Person person) {
        return personsShare.get(person);
    }

    public void addNewPersonShare(Person person, Double share) {
        personsShare.put(person, share);

        if(share == null) {
            personsShare.replaceAll((key, value) -> (1.0/personsShare.size()));
        }
    }

    public void clearExistingShareDetails() {
        personsShare.clear();
    }
}
