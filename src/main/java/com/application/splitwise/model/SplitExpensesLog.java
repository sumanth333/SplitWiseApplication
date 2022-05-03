package com.application.splitwise.model;


public class SplitExpensesLog {

    private String debtorName;
    private String beneficiaryName;
    private double amountTobePaid;

    public SplitExpensesLog(String debtorName, String beneficiaryName, double amountTobePaid) {
        this.debtorName = debtorName;
        this.beneficiaryName = beneficiaryName;
        this.amountTobePaid = amountTobePaid;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public double getAmountTobePaid() {
        return amountTobePaid;
    }
}
