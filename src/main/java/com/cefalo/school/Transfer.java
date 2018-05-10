package com.cefalo.school;

public class Transfer extends Transaction{
    private double amount;
    private Account fromAccount;
    private Account toAccount;

    public Transfer(double amount, Account fromAccount, Account toAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    @Override
    public void processTransaction(){
        TransferProcessor processor = new TransferProcessor();
        processor.processTransaction(this);
    }
}
