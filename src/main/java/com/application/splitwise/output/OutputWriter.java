package com.application.splitwise.output;

import com.application.splitwise.model.SplitExpensesLog;

import java.util.List;

public interface OutputWriter {
    void writeSplitExpensesLog(List<SplitExpensesLog> splitExpensesLogs);
}
