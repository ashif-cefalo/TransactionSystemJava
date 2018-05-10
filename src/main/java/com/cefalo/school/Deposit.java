package com.cefalo.school;

public class Deposit extends Transaction{
    private double amount;
    private Account account;

    public Deposit(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public void processTransaction(){
        DepositProcessor processor = new DepositProcessor();
        processor.processTransaction(this);
    }
}
