package com.bank.system;

public class BankClient {
    //сумма операции
    private int transactionAmount;
    //время обслуживания
    private int timeOfService;
    //тип операции
    private TypeOperation typeOperation;

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public int getTimeOfService() {
        return timeOfService;
    }

    public void setTimeOfService(int timeOfService) {
        this.timeOfService = timeOfService;
    }
}
