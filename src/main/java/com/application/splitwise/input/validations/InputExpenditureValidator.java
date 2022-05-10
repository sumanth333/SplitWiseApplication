package com.application.splitwise.input.validations;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;

public class InputExpenditureValidator {
    public boolean isValidPersonExpenditureInput(String input)
            throws InvalidInputValueException, InvalidInputFormatException {

        return isValidFormat(input) && isValidInputValues(input);
    }

    private boolean isValidFormat(String input) throws InvalidInputFormatException {
        if ((input.split(",")).length != 2)
            throw new InvalidInputFormatException("Given Input Format is invalid :" + input
                    + ", Example format: personName,100.0");
        return true;
    }

    private boolean isValidInputValues(String input) throws InvalidInputValueException {
        String[] inputData = input.split(",");
        try {
            if (inputData[0].isEmpty() || Double.parseDouble(inputData[1]) < 0.0)
                throw new InvalidInputValueException("Given Input Values are invalid :" + input);
        } catch (NumberFormatException e) {
            throw new InvalidInputValueException("Given Expenditure Value is invalid :" + input);
        }

        return true;
    }
}
