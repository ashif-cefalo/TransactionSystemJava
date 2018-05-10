package com.cefalo.school;

public class Withdraw extends Transaction{
    private double amount;
    private Account account;

    public Withdraw(double amount, Account account) {
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
        WithdrawProcessor processor = new WithdrawProcessor();
        processor.processTransaction(this);
    }
}
