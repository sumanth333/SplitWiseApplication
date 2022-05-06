package com.application.splitwise.input.validations;

import com.application.splitwise.exceptions.InvalidInputFormatException;
import com.application.splitwise.exceptions.InvalidInputValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class InputValidatorTest {

    @Test
    void shouldReturnTrueForValidPersonExpenditureInput()
            throws InvalidInputValueException, InvalidInputFormatException {

        String personExpenditureInput = "James,100.0";
        InputValidator inputValidator = new InputValidator();

        assertTrue(inputValidator.isValidPersonExpenditureInput(personExpenditureInput));
    }
    @Test
    void shouldThrowInValidFormatExceptionForInvalidInput() {

        String personExpenditureInput = "James 100.0";
        InputValidator inputValidator = new InputValidator();

        InvalidInputFormatException thrownException = Assertions.assertThrows(InvalidInputFormatException.class, () -> {
            inputValidator.isValidPersonExpenditureInput(personExpenditureInput);
        }, "InvalidInputFormatException was expected");

        Assertions.assertEquals("Given Input Format is invalid :James 100.0, " +
                "Example format: personName,100.0", thrownException.getMessage());

    }

    @Test
    void shouldThrowInValidValueExceptionForInvalidPersonName() {

        String personExpenditureInput = ",100.0";
        InputValidator inputValidator = new InputValidator();

        InvalidInputValueException thrownException = Assertions.assertThrows(InvalidInputValueException.class, () -> {
            inputValidator.isValidPersonExpenditureInput(personExpenditureInput);
        }, "InvalidInputValueException was expected");

        Assertions.assertEquals("Given Input Values are " +
                "invalid :,100.0", thrownException.getMessage());
    }

    @Test
    void shouldThrowInValidValueExceptionForInvalidExpenditure() {

        String personInvalidExpenditureInput1 = "testPerson,-100.0";
        String personInvalidExpenditureInput2 = "testPerson,I'mNotANumber";
        InputValidator inputValidator = new InputValidator();

        InvalidInputValueException thrownException = Assertions.assertThrows(InvalidInputValueException.class, () -> {
            inputValidator.isValidPersonExpenditureInput(personInvalidExpenditureInput1);
        }, "InvalidInputValueException was expected");

        Assertions.assertEquals("Given Input Values are " +
                "invalid :testPerson,-100.0", thrownException.getMessage());

        thrownException = Assertions.assertThrows(InvalidInputValueException.class, () -> {
            inputValidator.isValidPersonExpenditureInput(personInvalidExpenditureInput2);
        }, "InvalidInputValueException was expected");

        Assertions.assertEquals("Given Expenditure Value is " +
                "invalid :testPerson,I'mNotANumber", thrownException.getMessage());
    }
}