package com.application.splitwise.output;

import com.application.splitwise.model.SplitExpensesLog;

import java.util.List;

public class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void writeSplitExpensesLog(List<SplitExpensesLog> splitExpensesLogs) {
        for (SplitExpensesLog splitExpensesLog : splitExpensesLogs) {
            System.out.println(splitExpensesLog.getDebtorName() + "->" +
                    splitExpensesLog.getBeneficiaryName() + ", " + splitExpensesLog.getAmountTobePaid());
        }
    }
}
