package com.application.splitwise.input.validations;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;

public interface Validator {
    boolean isValidPersonExpenditureInput(String input) throws InvalidInputValueException, InvalidInputFormatException;
}
