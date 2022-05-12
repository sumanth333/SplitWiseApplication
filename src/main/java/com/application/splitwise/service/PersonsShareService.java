package com.application.splitwise.service;

import com.application.splitwise.model.Person;

import java.math.BigDecimal;
import java.util.HashMap;

public class PersonsShareService {

    private static PersonsShareService personsShareService = null;
    private HashMap<Person, Double> personsShare;

    private PersonsShareService(){}

    public static PersonsShareService getInstance() {
        if(personsShareService == null) {
            personsShareService = new PersonsShareService();
            personsShareService.personsShare = new HashMap<>();
        }
        return personsShareService;
    }

    public BigDecimal getPersonShare(Person person) {
        return BigDecimal.valueOf(personsShare.get(person));
    }

    public void addNewPersonShare(Person person, Double share) {
        personsShare.put(person, share);

        if(share == null) {
            personsShare.replaceAll((key, value) -> (1.0/personsShare.size()));
        }
    }

    public void createPersonShare(Person person, String input) {
        String[] inputDta = input.split(",");
        Double share = null;
        if(inputDta.length == 3)
            share = Double.parseDouble(inputDta[2]);

        addNewPersonShare(person, share);
    }

    public void clearExistingShareDetails() {
        personsShare.clear();
    }
}
