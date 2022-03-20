package com.bank.system;

public class BankCashVault  {
    private int cash;

    public BankCashVault(int cash) {
        this.cash = cash;
    }

    public  int getCash() {
        return cash;
    }

    private void setCash(int cash) {
        this.cash = cash;
    }

    public void depositMoney(int sum){
            this.cash += sum;
    }

    public boolean withdrawMoney(int sum){
        if(this.cash >= sum){
            this.cash -= sum;
            return true;
        }
        else {
            return false;
        }
    }
}
