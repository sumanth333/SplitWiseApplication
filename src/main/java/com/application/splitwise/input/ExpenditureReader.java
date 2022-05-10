package com.application.splitwise.input;

import com.application.splitwise.model.Expenditure;

import java.util.List;

public interface ExpenditureReader {
    List<Expenditure> readExpenditures();
}